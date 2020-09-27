package ru.geekbrains.student_homework.lesson_1;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse extends Frame implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            MainCircles.storage.addElement(new Ball());
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            MainCircles.storage.removeElement();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
