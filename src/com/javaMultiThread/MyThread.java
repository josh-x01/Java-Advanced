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
    }
}

class TheThread extends Thread {
    @Override
    public void run() {
        System.out.println("This is thread from class Thread");
    }
}

class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread("process");
        Thread thread1 = new Thread(myThread);
        thread1.start();

        TheThread theThread = new TheThread();
        theThread.start();
    }

}













/*
* interface runnable
* class thread
*
* */


class InterfaceThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Multi thread from runnable interface");
    }
}

class ClassThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            System.out.println(i);
            try {
                sleep(1);
            } catch (Exception e) {
                System.err.println("Failed to sleep the process!");
            }
        }
    }
}

class CallThread {
    public static void main(String[] args) {
        ClassThread classThread = new ClassThread();
        ClassThread classThread1 = new ClassThread();
        classThread.start();
        classThread1.start();
//        classThread.setPriority(10);


        InterfaceThread interfaceThread = new InterfaceThread();
        Thread thread = new Thread(interfaceThread);
        thread.start();
    }
}

