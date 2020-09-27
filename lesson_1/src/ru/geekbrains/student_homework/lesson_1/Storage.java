package ru.geekbrains.student_homework.lesson_1;

import java.util.Arrays;

public class Storage {
    private int index;
    private int length;
    Sprite[] sprites;

    // Создает пустой стэк
    Storage() {
        index = -1;
        this.length = 10;
        this.sprites = new Sprite[this.length];
    }

    // Создает пустой или частично/полностью заполненный стэк
    Storage(int numberOfFilledElements) {
        index = -1;
        this.length = numberOfFilledElements + 10;
        this.sprites = new Sprite[this.length];
        addCertainNumberOfEl(numberOfFilledElements);
    }

    // Добавляет один элемент в стэк
    // Если в массиве нет места, то увеличивает его на 10
    void addElement(Sprite element) {
        if (sprites[length - 1] != null) {
            length += 10;
            Sprite[] copySprites = sprites;
            sprites = new Sprite[length];
            sprites = Arrays.copyOf(copySprites, sprites.length);
        }
        sprites[++index] = element;
    }

    // Добавляет определенное количество элементов в стэк
    private void addCertainNumberOfEl(int numberOfFilledElements) {
        for (int i = 0; i < numberOfFilledElements; i++) {
            addElement(new Ball());
        }
    }

    // Удаляет последний добавленный элемент стэка
    void removeElement() {
        if (index == -1) {
            return;
        }
        sprites[index--] = null;
    }

    public int getIndex() {
        return index;
    }
}
