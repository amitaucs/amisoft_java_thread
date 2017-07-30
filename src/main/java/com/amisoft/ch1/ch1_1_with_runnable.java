package com.amisoft.ch1;

public class ch1_1_with_runnable {

    public static void main(String[] args){

        Thread t1 = new Thread(new RunnerParallel1());
        Thread t2 = new Thread(new RunnerParallel2());

        t1.start();
        t2.start();
    }

}


class RunnerParallel1 implements Runnable{

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println("Runner1 :" + i);
        }
    }
}

class RunnerParallel2 implements Runnable{

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println("Runner2 :" + i);
        }
    }
}