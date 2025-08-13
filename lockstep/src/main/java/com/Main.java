package com;

import com.room.Game;
import com.room.Player;
import com.room.Room;
import com.sun.net.httpserver.HttpServer;
import io.netty.buffer.ByteBuf;
import kcp.ChannelConfig;
import kcp.KcpListener;
import kcp.Ukcp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {
    static Logger logger = LoggerFactory.getLogger(Room.class);

    public static final int CONV = 10;
    public static final int UDP_PORT = 10086;

    public static void main(String[] args) throws Exception {
        createHttpServer();
        createUDPServer();
    }


    // http://localhost/create?room=1&member=1,2  1
    public static void createHttpServer() throws IOException {
        int port = 80;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        // 设置/create路径的处理器
        server.createContext("/create", httpExchange -> {
            try {
                String query = httpExchange.getRequestURI().getQuery();
                String[] params = query.split("&");

                int roomId = 0;
                List<Integer> members = new ArrayList<>();

                for (String param : params) {
                    if (param.startsWith("room=")) {
                        roomId = Integer.parseInt(param.split("=")[1]);
                    } else if (param.startsWith("member=")) {
                        members = Arrays.stream(param.split("=")[1].split(",")).map(Integer::parseInt).collect(Collectors.toList());
                    }
                }

                Game.createRoom(roomId, members);

                // 修复点1：显式设置响应头并发送响应头
                httpExchange.getResponseHeaders().set("Content-Type", "text/plain");
                httpExchange.sendResponseHeaders(200, "success".getBytes().length); // 关键修复

                try (OutputStream os = httpExchange.getResponseBody()) {
                    os.write("success".getBytes());
                }

            } catch (Exception e) {
                logger.error("", e);
            }
        });

        server.setExecutor(Executors.newFixedThreadPool(1));
        server.start();

        logger.info("Http Server started port: " + port);
    }

    public static void createUDPServer() throws InterruptedException {
        ChannelConfig channelConfig = new ChannelConfig();
        channelConfig.nodelay(true, 40, 2, true);
        channelConfig.setSndwnd(1024);
        channelConfig.setRcvwnd(1024);
        channelConfig.setMtu(1400);
        //channelConfig.setFecDataShardCount(10);
        //channelConfig.setFecParityShardCount(3);
        //channelConfig.setAckNoDelay(true);
        //channelConfig.setCrc32Check(true);
        channelConfig.setUseConvChannel(true);
        channelConfig.setTimeoutMillis(10000);
        kcp.KcpServer kcpServer = new kcp.KcpServer();
        kcpServer.init(new KcpListener() {
            @Override
            public void onConnected(Ukcp ukcp) {
                logger.info("kcp server connected +" + ukcp.toString() + ", user: " + ukcp.user().toString());
            }

            @Override
            public void handleReceive(ByteBuf byteBuf, Ukcp ukcp) {
                PacketWraper packetWraper = PacketWraper.readPacket(byteBuf);
                try {
                    Game.onMessageProcess(ukcp, packetWraper.id, packetWraper.data);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
               /* byte[] array = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(array);
                System.out.println(new String(array, StandardCharsets.UTF_8));*/
            }

            @Override
            public void handleException(Throwable throwable, Ukcp ukcp) {
                logger.error("", throwable);
            }

            @Override
            public void handleClose(Ukcp ukcp) {
                Game.onNetWorkClose(ukcp);
            }
        }, channelConfig, UDP_PORT);

        logger.info("UDP Server started port: " + UDP_PORT);
    }

}
