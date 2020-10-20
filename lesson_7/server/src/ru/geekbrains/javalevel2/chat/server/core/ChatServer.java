package ru.geekbrains.javalevel2.chat.server.core;

import ru.geekbrains.javalevel2.chat.common.Library;
import ru.geekbrains.javalevel2.network.ServerSocketThread;
import ru.geekbrains.javalevel2.network.ServerSocketThreadListener;
import ru.geekbrains.javalevel2.network.SocketThread;
import ru.geekbrains.javalevel2.network.SocketThreadListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class ChatServer implements ServerSocketThreadListener, SocketThreadListener {
    private ServerSocketThread thread;
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss: ");
    private Vector<SocketThread> clients;

    private final ChatServerListener listener;

    public ChatServer(ChatServerListener listener) {
        this.listener = listener;
        clients = new Vector<>();
    }

    public void start(int port) {
        if (thread != null && thread.isAlive()) {
            putLog("Server already started");
        } else {
            thread = new ServerSocketThread(this, "Thread of server", port, 2000);
        }
    }

    public void stop() {
        if (thread == null || !thread.isAlive()) {
            putLog("Server is not running");
        } else {
            thread.interrupt();
        }
    }

    private void putLog(String message) {
        message = DATE_FORMAT.format(System.currentTimeMillis()) + Thread.currentThread().getName() + ": " + message;
        listener.onChatServerMessage(message);
    }

    @Override
    public void onServerStart(ServerSocketThread thread) {
        putLog("Server started");
        SqlClient.connect();
    }

    @Override
    public void onServerStop(ServerSocketThread thread) {
        putLog("Server stopped");
        SqlClient.disconnect();
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
    public void onServerException(ServerSocketThread thread, Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onServerAccepted(ServerSocketThread thread, ServerSocket server, Socket socket) {
        putLog("Client connected");
        String name = "SocketThread " + socket.getInetAddress() + socket.getPort();
        new ClientThread(this, name, socket);
    }

    @Override
    public void onSocketStart(SocketThread socketThread, Socket socket) {
        putLog("Socket started");
    }

    @Override
    public void onSocketStop(SocketThread socketThread) {
        putLog("Socket stopped");
    }

    @Override
    public void onSocketReady(SocketThread socketThread, Socket socket) {
        putLog("Socket ready");
        clients.add(socketThread);
    }

    @Override
    public void onReceiveString(SocketThread thread, Socket socket, String message) {
        ClientThread client = (ClientThread) thread;
        if (client.isAuthorized())
            handleAuthMessage(client, message);
        else
            handleNonAuthMessage(client, message);
    }

    private void handleNonAuthMessage(ClientThread client, String message) {
        String[] arr = message.split(Library.DELIMITER);
        if (arr.length != 3 || !arr[0].equals(Library.AUTH_REQUEST)) {
            client.msgFormatError(message);
            return;
        }
        String login = arr[1];
        String password = arr[2];
        String nickname = SqlClient.getNickname(login, password);
        if (nickname == null) {
            putLog("Invalid login attempt: " + login);
            client.authFail();
            return;
        }
        client.authAccept(nickname);
        sendToAllAuthorizedClients(sendFormat(Library.getTypeBroadcast("Server: ", nickname + " connected")));
    }

    private String sendFormat(String message) {
        String[] arr = message.split(Library.DELIMITER);
        String msg = arr[arr.length - 2] + arr[arr.length - 1];
        return msg;
    }

    private void handleAuthMessage(ClientThread client, String message) {
        sendToAllAuthorizedClients(sendFormat(Library.getTypeBroadcast(client.getNickName() + ": ", message)));
    }

    private void sendToAllAuthorizedClients(String message) {
        for (int i = 0; i < clients.size(); i++) {
            ClientThread recipient = (ClientThread) clients.get(i);
            if (!recipient.isAuthorized()) continue;
            recipient.sendMessage(message);
        }
    }

    @Override
    public void onSocketException(SocketThread socketThread, Exception exception) {
        exception.printStackTrace();
    }
}
