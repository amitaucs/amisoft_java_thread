package com.amisoft;

import java.util.concurrent.TimeUnit;

public class ch1_1_demo_join {


    public static void main(String args[]){

        Thread t1 = new Thread(new DemoJoinRunner1());
        Thread t2 = new Thread(new DemoJoinRunner2());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("**** All job done. Note : Without join this line will print before the thread complete ******");
    }


}


class DemoJoinRunner1 implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println("Runner1 :" + i);
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class DemoJoinRunner2 implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println("Runner2 :" + i);
            try {
                TimeUnit.MICROSECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
