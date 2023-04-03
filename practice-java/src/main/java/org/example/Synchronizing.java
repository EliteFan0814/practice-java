package org.example;

public class Synchronizing {
    private static int i;
    private static Object lock = new Object();

    private synchronized static void modifySharedVariable() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        synchronized (lock) {
//            i++;
//        }
        i++;
        System.out.println(i);
    }

    public void goThreading() {
        Thread t = new Thread(() -> System.out.println(111));
        for (int j = 0; j < 100; j++) {
            new Thread(Synchronizing::modifySharedVariable).start();
        }
    }

    public int getI() {
        return i;
    }
}
