package ru.netology;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class CallCenter {

    private static final int calls = 60;
    private static final Queue<String> phoneCalls = new ArrayBlockingQueue<>(calls);

    public void call() {
        try {
            for (int i = 0; i < calls; i++) {
                phoneCalls.offer("Звонок №" + i);
                System.out.println("Поступил звонок №" + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void takeTheCall() {
        while (!phoneCalls.isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (phoneCalls.peek() != null) {
                System.out.println("Оператор " + Thread.currentThread().getName() + " ответил на звонок " + phoneCalls.poll());
            } else {
                Thread.currentThread().interrupt();
                System.out.println("Оператор закончил работу, звонки кончились");
            }

        }
    }

}
