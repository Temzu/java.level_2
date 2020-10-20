package ru.geekbrains.javalevel2.network;

import java.net.Socket;

public interface SocketThreadListener {
    void onSocketStart(SocketThread socketThread, Socket socket);
    void onSocketStop(SocketThread socketThread);
    void onSocketReady(SocketThread socketThread, Socket socket);
    void onReceiveString(SocketThread socketThread, Socket socket, String message);
    void onSocketException(SocketThread socketThread, Exception exception);
}
