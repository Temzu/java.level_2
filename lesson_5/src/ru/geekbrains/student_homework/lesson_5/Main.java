package ru.geekbrains.student_homework.lesson_5;

public class Main extends Thread{

    static final int size = 10000000;
    static final int h = size / 2;
    private final float[] arr;

    public static void main(String[] args) {
        workWithArraySlowly(size);
        System.out.println();
        workWithArrayQuickly(size);
    }

    public Main(float[] arr) {
        start();
        this.arr = arr;
    }

    @Override
    public void run() {
        changeArrayEl(h, arr);
    }

    static void workWithArrayQuickly(int size) {
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        for (int i = 0; i < size; i++)
            arr[i] = 1;
        
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        System.out.println("Время затраченное на разбитие массива: " + (System.currentTimeMillis() - a));
        
        Main thread1 = new Main(a1);
        Main thread2 = new Main(a2);
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        a = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println("Время затраченное на склейку массивов: " + (System.currentTimeMillis() - a));
    }

    static void workWithArraySlowly(int size) {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++)
            arr[i] = 1;
        changeArrayEl(size, arr);
    }

    static void changeArrayEl(int size, float[] arr) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++)
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        System.out.println("Время затраченное на изменение элементов массива: " + (System.currentTimeMillis() - a));
    }
}
