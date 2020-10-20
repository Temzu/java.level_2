package ru.geekbrains.javalevel2.chat.server.core;

import ru.geekbrains.javalevel2.chat.common.Library;
import ru.geekbrains.javalevel2.network.SocketThread;
import ru.geekbrains.javalevel2.network.SocketThreadListener;

import java.net.Socket;

public class ClientThread extends SocketThread {
    private String nickName;
    private boolean isAuthorized;

    public ClientThread(SocketThreadListener listener, String name, Socket socket) {
        super(listener, name, socket);
    }

    public String getNickName() {
        return nickName;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    void authAccept(String nickName) {
        isAuthorized = true;
        this.nickName = nickName;
        sendMessage(Library.getAuthAccept(nickName));
    }

    void authFail() {
        sendMessage(Library.getAuthDenied());
        close();
    }

    void msgFormatError(String message) {
        sendMessage(Library.getMsgFormatError(message));
        close();
    }
}
