package com.amisoft.ch1;

import java.util.concurrent.TimeUnit;

public class ch1_1_demo_wait_notify {


    public static void main(String[] args){

        final Process process = new Process();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    process.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    process.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
    }
}


class Process{

    public void produce() throws  InterruptedException{

        synchronized (this){
            System.out.println("We are in producer.......");
            wait(10000); // release the lock, so that other thread can proceed and acquire lock on the same object.
            System.out.println(" Back in producer again....");
        }

    }

    public void consume() throws  InterruptedException {

        TimeUnit.MILLISECONDS.sleep(1000);

        synchronized (this){
            System.out.println("We are in consumer.......");
            notify(); // signal the waiting thread, so that it can acquire the lock on the same object
            // if there is any code after notify , the code will execute before lock handover.
            System.out.println("Consumer : need a sleep");
            TimeUnit.MILLISECONDS.sleep(300);
        }


    }

}
