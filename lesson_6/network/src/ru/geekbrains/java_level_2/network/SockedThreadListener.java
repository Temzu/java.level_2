package ru.geekbrains.java_level_2.network;

import java.net.Socket;

public interface SockedThreadListener {
    void onSocketStart(SocketThread thread, Socket socket);
    void onSocketStop(SocketThread thread);
    void onSocketReady(SocketThread thread, Socket socket);
    void onReceiveString(SocketThread thread, Socket socket, String message);
    void onSocketException(SocketThread thread, Exception exception);
}
