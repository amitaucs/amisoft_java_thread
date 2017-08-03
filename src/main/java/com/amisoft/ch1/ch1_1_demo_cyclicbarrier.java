package com.amisoft.ch1;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ch1_1_demo_cyclicbarrier {

    public static void main(String[] args){

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new Runnable(){
           public void run(){
               System.out.println("All job done....");
           }
        });
        ExecutorService service = Executors.newCachedThreadPool();

        for(int i = 0; i<5; i++){
            service.execute(new WorkerCyclicBarrier(i,cyclicBarrier));
        }

    }
}


class WorkerCyclicBarrier implements Runnable{

    private int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public WorkerCyclicBarrier(int id, CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
        this.random = new Random();
        this.id = id;
    }

    @Override
    public void run() {
        doWork();
    }


    private void doWork() {
        System.out.println("Thread with ID " + id + " starts the task...");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread with ID " + id + " finished...");

        try {
            cyclicBarrier.await();
            System.out.println("After await...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() { return ""+this.id; };
}