package ru.geekbrains.javalevel2.chat.client;

import ru.geekbrains.javalevel2.chat.common.Library;
import ru.geekbrains.javalevel2.network.SocketThread;
import ru.geekbrains.javalevel2.network.SocketThreadListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler, SocketThreadListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;

    private static final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private static final JPasswordField pfPassword = new JPasswordField("123");
    private static final JCheckBox cbAOT = new JCheckBox("Always on top");
    private static final JTextField tfIpAddress = new JTextField("127.0.0.1");
    private static final JTextField tfLogin = new JTextField("name");
    private static final JButton btnLogin = new JButton("login");
    private static final JTextField tfPort = new JTextField("8189");

    private static final JTextArea log = new JTextArea();

    private static final JPanel panelBottom = new JPanel(new BorderLayout());
    private static final JButton btnDisconnect = new JButton("Disconnect");
    private static final JButton btnSend = new JButton("Send");
    private static final JTextField tfMessage = new JTextField();

    private final JList<String> userList = new JList<>();

    private SocketThread socketThread;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    public ClientGUI() {

        initActionsLister();

        initWindowElements();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Thread.setDefaultUncaughtExceptionHandler(this);
        setLocationRelativeTo(null);
        setTitle("Chat client");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    private void initWindowElements() {
        String[] users = {"user1", "user2", "Sick_user32143124112`1515221125152152"};
        log.setEditable(false);
        log.setLineWrap(true);
        userList.setListData(users);
        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUsers = new JScrollPane(userList);
        scrollUsers.setPreferredSize(new Dimension(150, 0));
        panelTop.add(tfIpAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAOT);
        panelTop.add(tfLogin);
        panelTop.add(pfPassword);
        panelTop.add(btnLogin);

        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        panelBottom.setVisible(false);

        add(panelTop, BorderLayout.NORTH);
        add(scrollUsers, BorderLayout.EAST);
        add(scrollLog, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
    }

    private void initActionsLister() {
        btnDisconnect.addActionListener(this);
        tfMessage.addActionListener(this);
        btnLogin.addActionListener(this);
        btnSend.addActionListener(this);
        cbAOT.addActionListener(this);
    }

    private void putLog(String logMessage) {
        if (logMessage.equals("")) return;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(logMessage + "\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }

    private void connect() {
        try {
            Socket socket = new Socket(tfIpAddress.getText(), Integer.parseInt(tfPort.getText()));
            socketThread = new SocketThread(this, "Client", socket);
        } catch (IOException e) {
            showException(Thread.currentThread(), e);
        }
    }

    private void disconnect() {
        socketThread.close();
    }

    private void showException(Thread t, Throwable e) {
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = "Exception in " + t.getName() + " " +
                e.getClass().getCanonicalName() + ": " +
                e.getMessage() + "\n\t at " + ste[0];
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
    }

    private void sendMessage() {
        String msg = tfMessage.getText();
        String userName = tfLogin.getText();
        if (msg.equals("")) return;
        tfMessage.setText("");
        tfMessage.grabFocus();
        socketThread.sendMessage(msg); // make it
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnLogin) {
            connect();
        } else if (src == btnDisconnect) {
            disconnect();
        } else if (src == btnSend || src == tfMessage){
            sendMessage();
        } else if (src == cbAOT) {
            setAlwaysOnTop(cbAOT.isSelected());
        } else {
            throw new RuntimeException("Unknown source:" + src);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        showException(t, e);
        System.exit(1);
    }


    @Override
    public void onSocketStart(SocketThread thread, Socket socket) {
        putLog("Start");
    }

    @Override
    public void onSocketStop(SocketThread thread) {
        putLog("Stop");
        panelTop.setVisible(true);
        panelBottom.setVisible(false);
    }

    @Override
    public void onSocketReady(SocketThread thread, Socket socket) {
        putLog("Ready");
        panelTop.setVisible(false);
        panelBottom.setVisible(true);
        String login = tfLogin.getText();
        String password = new String(pfPassword.getPassword());
        thread.sendMessage(Library.getAuthRequest(login, password));
    }

    @Override
    public void onReceiveString(SocketThread thread, Socket socket, String message) {
        putLog(message);
    }

    @Override
    public void onSocketException(SocketThread thread, Exception exception) {
        showException(thread, exception);
    }
}
