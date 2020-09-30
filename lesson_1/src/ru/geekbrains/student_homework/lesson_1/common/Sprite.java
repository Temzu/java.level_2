package ru.geekbrains.student_homework.lesson_1.common;

import java.awt.*;

public class Sprite implements GameObject{
    protected float x;
    protected float y;
    protected float halfWidth;
    protected float halfHeight;

    public float getLeft() {
        return x - halfWidth;
    }
    protected void setLeft(float left) {
        x = left + halfWidth;
    }
    public float getRight() {
        return x + halfWidth;
    }
    protected void setRight(float right) {
        x = right - halfWidth;
    }
    public float getTop() {
        return y - halfHeight;
    }
    protected void setTop(float top) {
        y = top + halfHeight;
    }
    public float getBottom() {
        return y + halfHeight;
    }
    protected void setBottom(float bottom) {
        y = bottom - halfHeight;
    }
    protected float getWidth() {
        return halfWidth * 2f;
    }
    protected float getHeight() {
        return halfHeight * 2f;
    }

    @Override
    public void update(GameCanvas canvas, float deltaTime) {

    }

    @Override
    public void render(GameCanvas canvas, Graphics g) {

    }
}
