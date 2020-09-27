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
            sprites = Arrays.copyOf(copySprites, length);
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
    void removeElement(int x, int y) {
        if (index == -1) {
            return;
        }
        for (int i = 0; i < sprites.length; i++) {
            if (sprites[i] == null){
                continue;
            }
            if (isXAndYFallsInInterval(i, x, y)) {
                sprites[i] = null;
                deleteNullValues();
                index--;
                break;
            }
        }
    }

    // Удаляет пустые элементы в массиве
    private void deleteNullValues() {
        Sprite[] copySprite = new Sprite[sprites.length];
        for (int i = 0; i < sprites.length; i++) {
            if (sprites[i] == null) {
                continue;
            }
            for (int j = 0; j < copySprite.length; j++) {
                if (copySprite[j] == null) {
                    copySprite[j] = sprites[i];
                    break;
                }
            }
        }
        sprites = Arrays.copyOf(copySprite, length);
    }

    // Проверяем попадали координаты x и y в координаты нашего элемента
    private boolean isXAndYFallsInInterval(int i, int x, int y) {
        boolean isX = (int) sprites[i].getLeft() <= x && (int)sprites[i].getRight() >= x;
        boolean isY = (int) sprites[i].getTop() <= y && (int)sprites[i].getBottom() >= y;
        return isX && isY;
    }

    public int getIndex() {
        return index;
    }
}
