package ru.geekbrains.javalevel2.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerSocketThread extends Thread {
    private final int port;
    private final int timeout;
    private final ServerSocketThreadListener listener;

    public ServerSocketThread(ServerSocketThreadListener listener, String name, int port, int timeout) {
        super(name);
        this.listener = listener;
        this.port = port;
        this.timeout = timeout;
        start();
    }

    @Override
    public void run() {
        listener.onServerStart(this);
        try (ServerSocket server = new ServerSocket(port)) {
            server.setSoTimeout(timeout);
            listener.onServerSocketCreated(this, server);
            while (!isInterrupted()) {
                Socket socket;
                try {
                    socket = server.accept();
                } catch (SocketTimeoutException e) {
                    listener.onServerTimeout(this, server);
                    continue;
                }
                listener.onServerAccepted(this, server, socket);
            }
        } catch (IOException e) {
            listener.onServerException(this, e);
        }
        listener.onServerStop(this);
    }
}
