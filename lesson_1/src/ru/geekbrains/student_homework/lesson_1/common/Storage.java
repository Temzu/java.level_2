package ru.geekbrains.student_homework.lesson_1.common;

import java.util.Arrays;

public class Storage {
    private int index;
    private int length;
    public GameObject[] gameObjects;

    // Создает пустой стэк
    public Storage() {
        index = -1;
        this.length = 10;
        this.gameObjects = new GameObject[this.length];
    }

    // Создает пустой или частично/полностью заполненный стэк
    public Storage(int numberOfFilledElements, GameObject gameObject) {
        index = -1;
        this.length = numberOfFilledElements + 10;
        this.gameObjects = new GameObject[this.length];
        addCertainNumberOfEl(numberOfFilledElements, gameObject);
    }

    // Добавляет один элемент в стэк
    // Если в массиве нет места, то увеличивает его на 10
    void addElement(GameObject gameObject) {
        if (gameObjects[length - 1] != null) {
            length += 10;
            GameObject[] copySprites = gameObjects;
            gameObjects = Arrays.copyOf(copySprites, length);
        }
        gameObjects[++index] = gameObject;
    }

    // Добавляет определенное количество элементов в стэк
    private void addCertainNumberOfEl(int numberOfFilledElements, GameObject gameObject) {
        for (int i = 0; i < numberOfFilledElements; i++) {
            addElement(gameObject);
        }
    }

    // Удаляет последний добавленный элемент стэка
    void removeElement(int x, int y) {
        if (index == -1) {
            return;
        }
        for (int i = 0; i < gameObjects.length; i++) {
            if (gameObjects[i] == null){
                continue;
            }
            if (isXAndYFallsInInterval(i, x, y)) {
                gameObjects[i] = null;
                deleteNullValues();
                index--;
                break;
            }
        }
    }

    // Проверяем попали ли координаты x и y в координаты нашего элемента
    private boolean isXAndYFallsInInterval(int i, int x, int y) {
        boolean isX = (int) gameObjects[i].getLeft() <= x && (int) gameObjects[i].getRight() >= x;
        boolean isY = (int) gameObjects[i].getTop() <= y && (int) gameObjects[i].getBottom() >= y;
        return isX && isY;
    }

    // Удаляет пустые элементы в массиве
    private void deleteNullValues() {
        GameObject[] copyObjects = new GameObject[gameObjects.length];
        for (int i = 0; i < gameObjects.length; i++) {
            if (gameObjects[i] == null) {
                continue;
            }
            for (int j = 0; j < gameObjects.length; j++) {
                if (copyObjects[j] == null) {
                    copyObjects[j] = gameObjects[i];
                    break;
                }
            }
        }
        gameObjects = Arrays.copyOf(copyObjects, length);
    }

    public int getIndex() {
        return index;
    }
}
