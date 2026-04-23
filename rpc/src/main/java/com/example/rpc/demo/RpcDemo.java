package com.example.rpc.demo;

import com.example.rpc.client.RpcClient;
import com.example.rpc.client.RpcCallback;
import com.example.rpc.client.JsonPayload;
import com.example.rpc.protocol.RpcCommand;
import com.example.rpc.server.RpcServer;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

public class RpcDemo {
    private static final int RPC_LOGIN = 10001;
    private static final InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("127.0.0.1", 9000);

    public static void main(String[] args) throws Exception {
        RpcServer server = startServer();

        RpcClient client = new RpcClient(3000L);
        try {
            sendOnly(client, loginFireCommand("AQS", "123456"));
            callByCallback(client, loginCallCommand("AQS", "123456"));
            callByFuture(client, loginCallCommand("AQS", "123456"));
            callBySync(client, loginCallCommand("AQS", "123456"));
        } finally {
            client.close();
            server.close();
        }
    }

    private static RpcServer startServer() {
        RpcServer server = new RpcServer(SERVER_ADDRESS.getPort());
        LoginServiceImpl loginService = new LoginServiceImpl();
        server.register(RPC_LOGIN, payload -> {
            LoginRequest request = JsonPayload.decode(payload, LoginRequest.class);
            LoginResult result = loginService.login(request);
            return JsonPayload.encode(result);
        });
        server.start();
        return server;
    }

    private static RpcCommand<Void> loginFireCommand(String account, String password) {
        LoginRequest request = new LoginRequest(account, password);
        return RpcCommand.fire(RPC_LOGIN, JsonPayload.encode(request));
    }

    private static RpcCommand<LoginResult> loginCallCommand(String account, String password) {
        LoginRequest request = new LoginRequest(account, password);
        return RpcCommand.call(RPC_LOGIN, JsonPayload.encode(request), LoginResult.class);
    }

    private static void sendOnly(RpcClient client, RpcCommand<Void> loginCommand) {
        client.send(SERVER_ADDRESS, loginCommand).exceptionally(error -> {
            System.out.println("send only failed: " + error.getMessage());
            return null;
        });
    }

    private static void callByCallback(RpcClient client, RpcCommand<LoginResult> loginCommand) throws Exception {
        CompletableFuture<LoginResult> future = client.call(SERVER_ADDRESS, loginCommand, new RpcCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult result) {
                System.out.println("callback login result: " + result);
            }

            @Override
            public void onFailure(Throwable error) {
                System.out.println("callback login failed: " + error.getMessage());
            }
        });
        future.get();
    }

    private static void callByFuture(RpcClient client, RpcCommand<LoginResult> loginCommand) throws Exception {
        CompletableFuture<LoginResult> future = client.call(SERVER_ADDRESS, loginCommand);
        future.whenComplete(RpcDemo::onLoginResult).get();
    }

    private static void callBySync(RpcClient client, RpcCommand<LoginResult> loginCommand) {
        LoginResult result = client.syncCall(SERVER_ADDRESS, loginCommand);
        System.out.println("sync login result: " + result);
    }

    private static void onLoginResult(LoginResult loginResult, Throwable error) {
        if (error != null) {
            System.out.println("async login failed: " + error.getMessage());
            return;
        }
        System.out.println("async login result: " + loginResult);
    }
}
