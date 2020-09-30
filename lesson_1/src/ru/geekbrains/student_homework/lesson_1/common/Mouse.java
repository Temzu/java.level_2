package ru.geekbrains.student_homework.lesson_1.common;

import ru.geekbrains.student_homework.lesson_1.games.circles.Ball;
import ru.geekbrains.student_homework.lesson_1.games.circles.MainCircles;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse extends JFrame implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            MainCircles.storage.addElement(new Ball(e.getX(), e.getY()));
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            MainCircles.storage.removeElement(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
