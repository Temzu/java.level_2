package ru.geekbrains.java_level_2.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketThread extends Thread implements Cloneable{
    private final SockedThreadListener listener;
    private final Socket socket;
    private DataOutputStream out;

    public SocketThread(SockedThreadListener listener, String name, Socket socket) {
        super(name);
        this.socket = socket;
        this.listener = listener;
        start();
    }

    @Override
    public void run() {
        try {
            listener.onSocketStart(this, socket);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            listener.onSocketReady(this, socket);
            while (!isInterrupted()) {
                String message = in.readUTF();
                listener.onReceiveString(this, socket, message);
            }
        } catch (IOException e) {
            listener.onSocketException(this, e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                listener.onSocketException(this, e);
            }
            listener.onSocketStop(this);
        }
    }

    public synchronized boolean sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
            return true;
        } catch (IOException e) {
            listener.onSocketException(this, e);
            close();
            return false;
        }
    }

    public synchronized void close() {
        interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            listener.onSocketException(this, e);
        }
    }
}
