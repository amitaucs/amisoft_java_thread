package com.amisoft.ch1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ch1_1_demo_countdownlatch {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {

            service.execute(new WorkerCountdown(1, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("All the prerequisites are done...");
        service.shutdown();

    }
}

class WorkerCountdown implements Runnable {

    private int id;
    private CountDownLatch latch;
    private Random random = new Random();

    public WorkerCountdown(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        doWork();
        latch.countDown();

    }

    public void doWork() {
        try {
            System.out.println("Thread with ID " + this.id + " starts working...");
            Thread.sleep(this.random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}



