package com.example.rpc.client;

import com.example.rpc.protocol.RpcCommand;
import com.example.rpc.protocol.RpcFrame;
import com.example.rpc.protocol.RpcFrameCodec;
import com.example.rpc.protocol.RpcMessageType;
import com.example.rpc.protocol.RpcRequest;
import com.example.rpc.protocol.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.Closeable;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RpcClient implements Closeable {
    private final AtomicInteger requestIdGenerator = new AtomicInteger();
    private final Map<Integer, PendingRpc> pendingRpcMap = new ConcurrentHashMap<Integer, PendingRpc>();
    private final Map<InetSocketAddress, Channel> channelMap = new ConcurrentHashMap<InetSocketAddress, Channel>();
    private final NioEventLoopGroup eventLoopGroup;
    private final Executor callbackExecutor;
    private final boolean shutdownCallbackExecutor;
    private final Bootstrap bootstrap;
    private final long defaultTimeoutMillis;
    private volatile boolean closed;

    public RpcClient(long defaultTimeoutMillis) {
        this(defaultTimeoutMillis, newDefaultCallbackExecutor(), true);
    }

    public RpcClient(long defaultTimeoutMillis, Executor callbackExecutor) {
        this(defaultTimeoutMillis, callbackExecutor, false);
    }

    private RpcClient(long defaultTimeoutMillis, Executor callbackExecutor, boolean shutdownCallbackExecutor) {
        if (defaultTimeoutMillis <= 0L || defaultTimeoutMillis > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("RPC 超时时间必须在 int 毫秒范围内: " + defaultTimeoutMillis);
        }
        if (callbackExecutor == null) {
            throw new IllegalArgumentException("RPC 回调线程池不能为空");
        }
        this.defaultTimeoutMillis = defaultTimeoutMillis;
        this.callbackExecutor = callbackExecutor;
        this.shutdownCallbackExecutor = shutdownCallbackExecutor;
        this.eventLoopGroup = new NioEventLoopGroup();
        this.bootstrap = createBootstrap();
    }

    public CompletableFuture<Void> send(InetSocketAddress remoteAddress, RpcCommand<?> command) {
        RpcRequest request = newRequest(command, false);
        final CompletableFuture<Void> writeFuture = new CompletableFuture<Void>();

        try {
            writeRequest(remoteAddress, request).addListener(future -> {
                if (future.isSuccess()) {
                    writeFuture.complete(null);
                    return;
                }
                writeFuture.completeExceptionally(
                        new RpcException("RPC 单向消息发送失败: " + request, future.cause()));
            });
        } catch (RuntimeException error) {
            writeFuture.completeExceptionally(error);
        }
        return toCallbackFuture(writeFuture);
    }

    public <T> CompletableFuture<T> call(InetSocketAddress remoteAddress, RpcCommand<T> command,
                                         final RpcCallback<T> callback) {
        CompletableFuture<T> resultFuture = call(remoteAddress, command);
        resultFuture.whenComplete((result, error) -> {
            if (error != null) {
                callback.onFailure(error);
                return;
            }
            callback.onSuccess(result);
        });
        return resultFuture;
    }

    public <T> CompletableFuture<T> call(final InetSocketAddress remoteAddress, final RpcCommand<T> command) {
        RpcRequest request = newRequest(command, true);
        CompletableFuture<RpcResponse> responseFuture = new CompletableFuture<RpcResponse>();

        registerPending(remoteAddress, request, responseFuture);
        scheduleTimeout(request);
        sendCallRequest(remoteAddress, request);

        return toResultFuture(command, responseFuture);
    }

    public <T> T syncCall(InetSocketAddress remoteAddress, RpcCommand<T> command) {
        try {
            return call(remoteAddress, command).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RpcException("RPC 同步调用被中断: " + command, e);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            }
            throw new RpcException("RPC 同步调用失败: " + command, cause);
        }
    }


    @Override
    public void close() {
        closed = true;
        closeChannels();
        failAll(new RpcException("RPC 客户端已关闭"));
        eventLoopGroup.shutdownGracefully().syncUninterruptibly();
        shutdownCallbackExecutor();
    }

    private Bootstrap createBootstrap() {
        return new Bootstrap()
                .group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) defaultTimeoutMillis)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                .addLast(new RpcFrameCodec())
                                .addLast(new ClientHandler());
                    }
                });
    }

    private static ExecutorService newDefaultCallbackExecutor() {
        return Executors.newCachedThreadPool(new NamedThreadFactory("rpc-callback"));
    }

    private RpcRequest newRequest(RpcCommand<?> command, boolean needResponse) {
        return new RpcRequest(nextRequestId(), command.getRpcId(), command.getPayload(), needResponse);
    }

    private int nextRequestId() {
        return requestIdGenerator.incrementAndGet();
    }

    private void registerPending(InetSocketAddress remoteAddress, RpcRequest request,
                                 CompletableFuture<RpcResponse> responseFuture) {
        PendingRpc pendingRpc = new PendingRpc(request.getRequestId(), remoteAddress,
                System.currentTimeMillis() + defaultTimeoutMillis, responseFuture);
        pendingRpcMap.put(request.getRequestId(), pendingRpc);
    }

    private void scheduleTimeout(final RpcRequest request) {
        eventLoopGroup.next().schedule(new Runnable() {
            @Override
            public void run() {
                failPending(request.getRequestId(),
                        new RpcTimeoutException("RPC 请求超时, requestId=" + request.getRequestId()));
            }
        }, defaultTimeoutMillis, TimeUnit.MILLISECONDS);
    }

    private void sendCallRequest(InetSocketAddress remoteAddress, final RpcRequest request) {
        try {
            writeRequest(remoteAddress, request).addListener(future -> {
                if (!future.isSuccess()) {
                    failPending(request.getRequestId(),
                            new RpcException("RPC 请求发送失败: " + request, future.cause()));
                }
            });
        } catch (RuntimeException error) {
            failPending(request.getRequestId(), new RpcException("RPC 请求发送失败: " + request, error));
        }
    }

    private CompletableFuture<Void> toCallbackFuture(CompletableFuture<Void> sourceFuture) {
        final CompletableFuture<Void> callbackFuture = new CompletableFuture<Void>();
        sourceFuture.whenCompleteAsync((value, error) -> completeFuture(callbackFuture, value, error), callbackExecutor);
        return callbackFuture;
    }

    private <T> CompletableFuture<T> toResultFuture(final RpcCommand<T> command,
                                                    CompletableFuture<RpcResponse> responseFuture) {
        final CompletableFuture<T> resultFuture = new CompletableFuture<T>();
        responseFuture.whenCompleteAsync((response, error) ->
                completeResultFuture(command, resultFuture, response, error), callbackExecutor);
        return resultFuture;
    }

    private <T> void completeResultFuture(RpcCommand<T> command, CompletableFuture<T> resultFuture,
                                          RpcResponse response, Throwable error) {
        if (error != null) {
            resultFuture.completeExceptionally(error);
            return;
        }
        try {
            resultFuture.complete(readResult(command, response));
        } catch (Throwable decodeError) {
            resultFuture.completeExceptionally(decodeError);
        }
    }

    private static <T> void completeFuture(CompletableFuture<T> future, T value, Throwable error) {
        if (error == null) {
            future.complete(value);
            return;
        }
        future.completeExceptionally(error);
    }

    private ChannelFuture writeRequest(InetSocketAddress remoteAddress, RpcRequest request) {
        return getChannel(remoteAddress).writeAndFlush(toFrame(request));
    }

    private <T> T readResult(RpcCommand<T> command, RpcResponse response) {
        if (!response.isSuccess()) {
            throw new RpcException("RPC 服务端调用失败: " + command + ", error=" + response.getErrorMessage());
        }

        byte[] payload = response.getPayload();
        if (payload.length == 0 || command.getResultType() == Void.class) {
            return null;
        }
        return JsonPayload.decode(payload, command.getResultType());
    }

    private void completeResponse(RpcResponse response) {
        PendingRpc pendingRpc = pendingRpcMap.remove(response.getRequestId());
        if (pendingRpc != null) {
            pendingRpc.getFuture().complete(response);
        }
    }

    private void failPending(int requestId, RpcException error) {
        PendingRpc pendingRpc = pendingRpcMap.remove(requestId);
        if (pendingRpc != null) {
            pendingRpc.getFuture().completeExceptionally(error);
        }
    }

    private synchronized Channel getChannel(InetSocketAddress remoteAddress) {
        if (closed) {
            throw new RpcException("RPC 客户端已关闭");
        }
        Channel oldChannel = channelMap.get(remoteAddress);
        if (oldChannel != null && oldChannel.isActive()) {
            return oldChannel;
        }

        ChannelFuture connectFuture = bootstrap.connect(remoteAddress).awaitUninterruptibly();
        if (!connectFuture.isSuccess()) {
            throw new RpcException("RPC 连接失败: " + remoteAddress, connectFuture.cause());
        }
        Channel newChannel = connectFuture.channel();
        channelMap.put(remoteAddress, newChannel);
        return newChannel;
    }

    private void closeChannels() {
        for (Channel channel : channelMap.values()) {
            channel.close().syncUninterruptibly();
        }
        channelMap.clear();
    }

    private void shutdownCallbackExecutor() {
        if (shutdownCallbackExecutor && callbackExecutor instanceof ExecutorService) {
            ((ExecutorService) callbackExecutor).shutdown();
        }
    }

    private RpcFrame toFrame(RpcRequest request) {
        return RpcFrame.request(request.getRequestId(), request.getRpcId(),
                request.isNeedResponse(), request.getPayload());
    }

    private RpcResponse toResponse(RpcFrame frame) {
        if (frame.getMessageType() != RpcMessageType.RESPONSE) {
            throw new RpcException("客户端收到非响应消息, messageType=" + frame.getMessageType());
        }
        if (frame.isSuccess()) {
            return RpcResponse.success(frame.getRequestId(), frame.getPayload());
        }
        return RpcResponse.failure(frame.getRequestId(), frame.getErrorMessage());
    }

    private final class ClientHandler extends SimpleChannelInboundHandler<RpcFrame> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, RpcFrame frame) {
            completeResponse(toResponse(frame));
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) {
            InetSocketAddress inactiveAddress = null;
            Iterator<Map.Entry<InetSocketAddress, Channel>> iterator = channelMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<InetSocketAddress, Channel> entry = iterator.next();
                if (entry.getValue() == ctx.channel()) {
                    inactiveAddress = entry.getKey();
                    iterator.remove();
                }
            }
            if (inactiveAddress == null) {
                failAll(new RpcException("RPC 连接已断开: " + ctx.channel().remoteAddress()));
                return;
            }
            failByRemoteAddress(inactiveAddress,
                    new RpcException("RPC 连接已断开: " + inactiveAddress));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            failAll(new RpcException("RPC 客户端网络异常", cause));
            ctx.close();
        }
    }

    private void failAll(RpcException error) {
        for (PendingRpc pendingRpc : pendingRpcMap.values()) {
            pendingRpc.getFuture().completeExceptionally(error);
        }
        pendingRpcMap.clear();
    }

    private void failByRemoteAddress(InetSocketAddress remoteAddress, RpcException error) {
        Iterator<Map.Entry<Integer, PendingRpc>> iterator = pendingRpcMap.entrySet().iterator();
        while (iterator.hasNext()) {
            PendingRpc pendingRpc = iterator.next().getValue();
            if (pendingRpc.belongsTo(remoteAddress)) {
                iterator.remove();
                pendingRpc.getFuture().completeExceptionally(error);
            }
        }
    }

    private static final class NamedThreadFactory implements ThreadFactory {
        private final String threadNamePrefix;
        private final AtomicInteger threadIndex = new AtomicInteger();

        private NamedThreadFactory(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, threadNamePrefix + "-" + threadIndex.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        }
    }
}
