package ru.geekbrains.student_homework.lesson_1.games.circles;

import java.awt.*;

public class Background{
    private float r = 0;
    private float g = 0;
    private float b = 0;
    private float rateOfChangeR = (float)(Math.random() * 200f);
    private float rateOfChangeG = (float)(Math.random() * 200f);
    private float rateOfChangeB = (float)(Math.random() * 200f);

    public Color setColor(float deltaTime) {
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

        return new Color((int) r,(int) g,(int) b);
    }
}
