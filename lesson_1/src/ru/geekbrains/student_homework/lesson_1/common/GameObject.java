package ru.geekbrains.student_homework.lesson_1.common;

import java.awt.*;

public interface GameObject {
    void update(GameCanvas canvas, float deltaTime);
    void render(GameCanvas canvas, Graphics g);
    float getLeft();
    float getRight();
    float getTop();
    float getBottom();
}
