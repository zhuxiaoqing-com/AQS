package com.example.rpc.server;

import com.example.rpc.protocol.RpcFrame;
import com.example.rpc.protocol.RpcFrameCodec;
import com.example.rpc.protocol.RpcMessageType;
import com.example.rpc.protocol.RpcRequest;
import com.example.rpc.protocol.RpcResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.Closeable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServer implements Closeable {
    private final int port;
    private final Map<Integer, RpcHandler> handlerMap = new ConcurrentHashMap<Integer, RpcHandler>();
    private final ExecutorService businessExecutor = Executors.newCachedThreadPool();
    private final NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private final NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    private volatile boolean running;
    private Channel serverChannel;

    public RpcServer(int port) {
        this.port = port;
    }

    public void register(int rpcId, RpcHandler handler) {
        if (handler == null) {
            throw new IllegalArgumentException("RPC handler 不能为空, rpcId=" + rpcId);
        }
        handlerMap.put(rpcId, handler);
    }

    public void start() {
        ChannelFuture bindFuture = createServerBootstrap().bind(port).awaitUninterruptibly();
        if (!bindFuture.isSuccess()) {
            throw new IllegalStateException("RPC 服务端启动失败, port=" + port, bindFuture.cause());
        }
        serverChannel = bindFuture.channel();
        running = true;
    }

    @Override
    public void close() {
        running = false;
        businessExecutor.shutdownNow();
        closeServerChannel();
        bossGroup.shutdownGracefully().syncUninterruptibly();
        workerGroup.shutdownGracefully().syncUninterruptibly();
    }

    private ServerBootstrap createServerBootstrap() {
        return new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                .addLast(new RpcFrameCodec())
                                .addLast(new ServerHandler());
                    }
                });
    }

    private void closeServerChannel() {
        if (serverChannel != null) {
            serverChannel.close().syncUninterruptibly();
        }
    }

    private final class ServerHandler extends SimpleChannelInboundHandler<RpcFrame> {
        @Override
        protected void channelRead0(final ChannelHandlerContext ctx, final RpcFrame frame) {
            dispatch(ctx, toRequest(frame));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            ctx.close();
        }
    }

    private void dispatch(ChannelHandlerContext ctx, RpcRequest request) {
        businessExecutor.execute(new InvokeTask(ctx, request));
    }

    private final class InvokeTask implements Runnable {
        private final ChannelHandlerContext ctx;
        private final RpcRequest request;

        private InvokeTask(ChannelHandlerContext ctx, RpcRequest request) {
            this.ctx = ctx;
            this.request = request;
        }

        @Override
        public void run() {
            RpcResponse response = handleRequest(request);
            if (response != null) {
                ctx.writeAndFlush(toFrame(response));
            }
        }
    }

    private RpcResponse handleRequest(RpcRequest request) {
        try {
            byte[] result = invoke(request);
            if (!request.isNeedResponse()) {
                return null;
            }
            return RpcResponse.success(request.getRequestId(), result);
        } catch (Throwable error) {
            if (!request.isNeedResponse()) {
                throw new IllegalStateException("RPC 单向通知处理失败, requestId=" + request.getRequestId(), error);
            }
            return RpcResponse.failure(request.getRequestId(), error.getMessage());
        }
    }

    private byte[] invoke(RpcRequest request) throws Throwable {
        RpcHandler handler = handlerMap.get(request.getRpcId());
        if (handler == null) {
            throw new IllegalArgumentException("未注册 RPC 处理器, rpcId=" + request.getRpcId());
        }
        return handler.handle(request.getPayload());
    }

    private RpcRequest toRequest(RpcFrame frame) {
        if (frame.getMessageType() != RpcMessageType.REQUEST) {
            throw new IllegalArgumentException("服务端收到非请求消息, messageType=" + frame.getMessageType());
        }
        return new RpcRequest(frame.getRequestId(), frame.getRpcId(),
                frame.getPayload(), frame.isNeedResponse());
    }

    private RpcFrame toFrame(RpcResponse response) {
        if (response.isSuccess()) {
            return RpcFrame.success(response.getRequestId(), response.getPayload());
        }
        return RpcFrame.failure(response.getRequestId(), response.getErrorMessage());
    }
}
