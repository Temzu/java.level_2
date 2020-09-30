package ru.geekbrains.student_homework.lesson_1.games.circles;

import ru.geekbrains.student_homework.lesson_1.common.GameCanvas;
import ru.geekbrains.student_homework.lesson_1.common.GameCanvasListener;
import ru.geekbrains.student_homework.lesson_1.common.Mouse;
import ru.geekbrains.student_homework.lesson_1.common.Storage;

import javax.swing.*;
import java.awt.*;

public class MainCircles extends JFrame implements GameCanvasListener {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public static Storage storage;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        GameCanvas canvas = new GameCanvas(this);
        add(canvas);
        canvas.addMouseListener(new Mouse());
        initApplication();
        setTitle("Circles");
        setVisible(true);
    }

    private void initApplication() {
        storage = new Storage();
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i <= storage.getIndex(); i++) {
            storage.gameObjects[i].update(canvas,deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i <= storage.getIndex(); i++) {
            storage.gameObjects[i].render(canvas, g);
        }
    }

    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }
}
