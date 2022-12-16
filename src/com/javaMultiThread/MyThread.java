package com.javaMultiThread;

public class MyThread implements Runnable{
    Thread thread;
    String threadName;
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(threadName + " count is " + i);
            } catch (Exception e) {
                System.out.println("Thread interrupted!");
            }
            System.out.println("Thread terminated!");
        }

    }

    public MyThread(String threadName) {
        this.threadName = threadName;

        thread = new Thread(this, threadName);
        thread.start();
    }
}
