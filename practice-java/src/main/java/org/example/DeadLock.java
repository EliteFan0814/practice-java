package org.example;

public class DeadLock {
    private final static Object lock1 = new Object();
    private final static Object lock2 = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    System.out.println("");
                }
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1) {
                    System.out.println("");
                }
            }
        }
    }

    public void startDeading() {
        new DeadLock.Thread1().start();
        new DeadLock.Thread2().start();
    }
}
