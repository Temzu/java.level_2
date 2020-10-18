package ru.geekbrains.java_level_2.chat.server.core;

import ru.geekbrains.java_level_2.network.ServerSocketThread;
import ru.geekbrains.java_level_2.network.ServerSocketThreadListener;
import ru.geekbrains.java_level_2.network.SockedThreadListener;
import ru.geekbrains.java_level_2.network.SocketThread;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class ChatServer implements ServerSocketThreadListener, SockedThreadListener {
    ServerSocketThread thread;
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss: ");
    private Vector<SocketThread> socketThreads = new Vector<>();

    public void start(int port) {
        if (thread != null && thread.isAlive()) {
            System.out.println("Server already started");
        } else {
            thread = new ServerSocketThread(this, "Thread of server", 8189, 2000);
        }
    }

    public void stop() {
        if (thread == null || !thread.isAlive()) {
            System.out.println("Server is not running");
        } else {
            thread.interrupt();
        }
    }

    private void putLog(String message) {
        message = DATE_FORMAT.format(System.currentTimeMillis()) + Thread.currentThread().getName() + ": " + message;
        System.out.println(message);
    }

    @Override
    public void onServerStart(ServerSocketThread thread) {
        putLog("Server started");
    }

    @Override
    public void onServerStop(ServerSocketThread thread) {
        putLog("Server stopped");
    }

    @Override
    public void onServerSocketCreated(ServerSocketThread thread, ServerSocket server) {
        putLog("Server socket created");
    }

    @Override
    public void onServerTimeout(ServerSocketThread thread, ServerSocket server) {
//        putLog("Server timeout");
    }

    @Override
    public void onServerException(ServerSocketThread thread, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public void onSocketAccepted(ServerSocketThread thread, ServerSocket server, Socket socket) {
        putLog("Client connected");
        String name = "SocketThread" + socket.getInetAddress() + ":" + socket.getPort();
        new SocketThread(this, name, socket);
    }

    @Override
    public void onSocketStart(SocketThread thread, Socket socket) {
        putLog("Socked created");
    }

    @Override
    public void onSocketStop(SocketThread thread) {
        putLog("Socked stopped");
    }

    @Override
    public void onSocketReady(SocketThread thread, Socket socket) {
        putLog("Socked ready");
    }

    @Override
    public void onReceiveString(SocketThread thread, Socket socket, String message) {
        thread.sendMessage("Echo: " + message);
    }

    @Override
    public void onSocketException(SocketThread thread, Exception exception) {
        exception.printStackTrace();
    }
}
