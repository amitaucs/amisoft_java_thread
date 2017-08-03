package com.amisoft.ch1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ch1_1_demo_producer_consumer_with_lock {


    public static void main(String[] args) {

        final Worker worker = new Worker();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    worker.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    worker.consumer();
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


class Worker {

    private Lock lock = new ReentrantLock(true);
    Condition condition = lock.newCondition();
    int counter = 0;

    public void producer() throws InterruptedException {

        lock.lock();
        try {
            System.out.println("In Producer method......");
            counter++;
            System.out.println("Counter is : " + counter);
            condition.await();
            System.out.println("In Producer again....");
        } finally {
            lock.unlock();
        }

    }

    public void consumer() throws InterruptedException {

        lock.lock();
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println("In consumer.....");
            counter--;
            System.out.println("Counter is : " + counter);
            condition.signal();
        } finally {
            lock.unlock();
        }


    }

}
