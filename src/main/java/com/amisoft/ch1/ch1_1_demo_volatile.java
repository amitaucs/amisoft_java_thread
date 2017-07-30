package com.amisoft.ch1;

import java.util.concurrent.TimeUnit;

public class ch1_1_demo_volatile {

    public static void main(String []args){

        DemoWorker worker = new DemoWorker();
        Thread t1 = new Thread(worker);
        t1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Note : Without Volatile below change may not be reflected in t1 immediately.
        worker.setTerminated(true);
        System.out.println("Finished....");

    }
}


class DemoWorker implements  Runnable{

   private volatile boolean isTerminated = false;

    @Override
    public void run() {

        while(!isTerminated){
            System.out.println("Hello from DemoWorker....");
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }
}