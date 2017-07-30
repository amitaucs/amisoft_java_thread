package com.amisoft.ch1;

public class ch1_1_demo_synchronizedblock {

    private static int count1 = 0;
    private static int count2 = 0;

//Avoid Synchronized method because at a time only one synchronized method will execute. Class level lock acquired.
   /* public synchronized static void add(){
        count1++;
    }

    public synchronized static void addAgain(){
        count2++;
    }*/

    // So better use synchrinized block.

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void add() {

        synchronized (lock1) {
            count1++;
        }
    }

    public synchronized static void addAgain() {
        synchronized (lock2) {
            count2++;
        }
    }

    public static void compute() {

        for (int i = 0; i < 100; i++) {
            add();
            addAgain();
        }
    }


    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });


        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count1 :" + count1);
        System.out.println("Count2 :" + count2);

    }
}
