package com.amisoft.ch1;

import java.util.concurrent.TimeUnit;

public class ch1_1_with_thread {

    public static void main(String[] args){


        Thread t1 = new RunnerThread1();
        Thread t2 = new RunnerThread2();

        t1.start();
        t2.start();

    }
}


class RunnerThread1 extends Thread{

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            System.out.println("Runner1 :" + i);
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class RunnerThread2 extends Thread{

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            System.out.println("Runner2 :" + i);
            try {
                TimeUnit.MICROSECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
