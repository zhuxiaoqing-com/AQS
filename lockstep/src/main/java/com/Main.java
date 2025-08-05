package com;

import com.room.Player;
import com.room.Room;
import com.sun.net.httpserver.HttpServer;
import io.netty.buffer.ByteBuf;
import kcp.ChannelConfig;
import kcp.KcpListener;
import kcp.Ukcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {

    public static final int CONV = 10;
    public static final int UDP_PORT = 10086;

    static int nextRoomId = 1;
    static Map<Integer, Room> roomMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        createHttpServer();
    }


    // http://localhost/create?room=1&member=1,2
    public static void createHttpServer() throws IOException {
        int port = 80;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        // 设置/create路径的处理器
        server.createContext("/create", httpExchange -> {
            String query = httpExchange.getRequestURI().getQuery();
            String[] params = query.split("&");

            int roomId = 0;
            List<Integer> members = new ArrayList<>();

            for (String param : params) {
                if (param.startsWith("room=")) {
                    roomId = Integer.parseInt(param.split("=")[1]);
                } else if (param.startsWith("member=")) {
                    members = Arrays.stream(param.split("=")).map(Integer::parseInt).collect(Collectors.toList());
                }
            }

            // 创建房间;
            List<Player> collect = members.stream().map(a -> new Player(a.longValue())).collect(Collectors.toList());
            roomMap.put(roomId, new Room(collect));
            System.out.println("Room: " + roomId + " created, players: " + collect);

            httpExchange.getResponseBody().write("success".getBytes());
            httpExchange.getResponseBody().flush();
            httpExchange.getResponseBody().close();
        });

        server.setExecutor(Executors.newFixedThreadPool(1));
        server.start();

        System.out.println("Http Server started port: " + port);
    }

    public static void createUDPServer() throws InterruptedException {
        ChannelConfig channelConfig = new ChannelConfig();
        channelConfig.nodelay(true,40,2,true);
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

            }

            @Override
            public void handleReceive(ByteBuf byteBuf, Ukcp ukcp) {
                byteBuf
            }

            @Override
            public void handleException(Throwable throwable, Ukcp ukcp) {

            }

            @Override
            public void handleClose(Ukcp ukcp) {

            }
        }, channelConfig, UDP_PORT);
    }

}
