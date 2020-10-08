
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class ChatServer {
    private static final Logger logger = Logger.getLogger(ClientGUI.class.getName());
    Handler fileHandler;
    boolean isServerStarted;

    public void start(int port) {
        System.out.println("Server started at: " + port);
        setLogger();
        isServerStarted = true;
    }

    private void setLogger() {
        try {
            fileHandler = new FileHandler("C:/Users/Артем/Desktop/GeekBrains/java/java.level_2/lesson_4/java%u.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);
    }

    public void logMessageInfo(String message) {
        logger.info(message);
    }

    public void stop() {
        System.out.println("Server stopped");
    }
}
