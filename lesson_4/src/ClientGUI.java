import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


public class ClientGUI extends JFrame implements ActionListener, KeyListener, Thread.UncaughtExceptionHandler{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final TextArea log = new TextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2,3));

    private final TextField tfIpAddress = new TextField("127.0.0.1");
    private final TextField tfPort = new TextField("8080");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final TextField tfLogin = new TextField("login");
    private final JPasswordField tfPassport = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final TextField tfMessage = new TextField();
    private final JButton btnSend = new JButton("Send");
    private static final ChatServer chatServer = new ChatServer();

    private final JList<String> userList = new JList<>();

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
        tfMessage.addKeyListener(this);
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
        chatServer.start(8080); //Условно включу сервер, просто пока не понял как это параллельно сделать
    }

    private void sendMessageEvent() {
        if (tfMessage.getText().equals("")) return;
        //проверяем влючен ли сервер, если да, то пишем лог
        if (chatServer.isServerStarted) chatServer.logMessageInfo(tfMessage.getText());

        String string = log.getText();
        string += tfMessage.getText() + "\n";
        log.setText(string);
        tfMessage.setText("");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (source == btnSend) {
            sendMessageEvent();
        } else if(source == btnLogin) {

        }else {
            throw new RuntimeException("Unknown source: " + source);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = "Exception in " + t.getName() + " " +
                e.getClass().getCanonicalName() + ": " +
                e.getMessage() + "\n\t at " + ste[0];
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object source = e.getKeyCode();
        if (source.equals(KeyEvent.VK_ENTER)) {
            sendMessageEvent();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
