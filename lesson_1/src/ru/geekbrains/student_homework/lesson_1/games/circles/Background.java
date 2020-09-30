package ru.geekbrains.student_homework.lesson_1.games.circles;

import ru.geekbrains.student_homework.lesson_1.common.GameCanvas;
import ru.geekbrains.student_homework.lesson_1.common.GameObject;

import java.awt.*;

public class Background implements GameObject {
    private float r = 0;
    private float g = 0;
    private float b = 0;
    private float rateOfChangeR = (float)(Math.random() * 200f);
    private float rateOfChangeG = (float)(Math.random() * 200f);
    private float rateOfChangeB = (float)(Math.random() * 200f);
    Color color;

    @Override
    public void update(GameCanvas canvas, float deltaTime) {
        r += rateOfChangeR * deltaTime;
        g += rateOfChangeG * deltaTime;
        b += rateOfChangeB * deltaTime;

        if (r >= 255) {
            rateOfChangeR = -rateOfChangeR;
            r -= (r - 255);
        }
        if (g >= 255) {
            rateOfChangeG = -rateOfChangeG;
            g -= (g - 255);
        }
        if (b >= 255) {
            rateOfChangeB = -rateOfChangeB;
            b -= (b - 255);
        }
        if (r < 0) {
            rateOfChangeR = -rateOfChangeR;
            r -= r;
        }
        if (g < 0) {
            rateOfChangeG = -rateOfChangeG;
            g -= g;
        }
        if (b < 0) {
            rateOfChangeB = -rateOfChangeB;
            b -= b;
        }
        color = new Color((int) r, (int) g, (int) b);
    }

    @Override
    public void render(GameCanvas canvas, Graphics g) {
        canvas.setBackground(color);
    }

    @Override
    public float getLeft() {
        return 0;
    }

    @Override
    public float getRight() {
        return 0;
    }

    @Override
    public float getTop() {
        return 0;
    }

    @Override
    public float getBottom() {
        return 0;
    }
}
