import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;


public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2,3));

    private final TextField tfIpAddress = new TextField("127.0.0.1");
    private final TextField tfPort = new TextField("8080");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final TextField tfLogin = new TextField("login");
    private final JPasswordField tfPassport = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private static final ChatServer chatServer = new ChatServer();

    private final JList<String> userList = new JList<>();

    private static boolean shownIoErrors;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    public ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        String[] users = {"User1", "User2", "User3", "User4", "Sick_User_asdfasdlkjsrlkfjsalkdjfkjaskjdfkjkjaskdjffgdgd"};
        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUsers = new JScrollPane(userList);
        scrollUsers.setPreferredSize(new Dimension(150, 0));
        userList.setListData(users);
        log.setEditable(false);
        btnSend.addActionListener(this);
        cbAlwaysOnTop.addActionListener(this);
        tfMessage.addActionListener(this);
        panelTop.add(tfIpAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassport);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);
        add(scrollLog, BorderLayout.CENTER);
        add(scrollUsers, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void sendMessageEvent() {
        String message = tfMessage.getText();
        if (message.equals("")) return;
        String userName = tfLogin.getText();
        putLog(String.format("%s: %s", userName, message));
        tfMessage.setText("");
        tfMessage.grabFocus();
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

    private void writeMessageToLogFile(String message, String userName) {
        try (FileWriter out = new FileWriter("log.txt", true)) {
            out.write(userName + ": " + message + "\n");
            out.flush();
        } catch (IOException e) {
            if (!shownIoErrors) {
                shownIoErrors = true;
                showException(Thread.currentThread(), e);
            }
        }
    }

    private void showException(Thread t, Throwable e) {
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = "Exception in " + t.getName() + " " +
                e.getClass().getCanonicalName() + ": " +
                e.getMessage() + "\n\t at " + ste[0];
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (source == btnSend || source == tfMessage) {
            sendMessageEvent();
        } else if(source == btnLogin) {

        }else {
            throw new RuntimeException("Unknown source: " + source);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        showException(t, e);
        System.exit(1);
    }

}
