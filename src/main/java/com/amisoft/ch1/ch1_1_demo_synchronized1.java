package com.amisoft.ch1;

public class ch1_1_demo_synchronized1 {

    private static int counter = 0;


    public static synchronized  void increment(){
        ++counter;
    }


    public static void process(){

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i< 1000; i++)
                   // ++counter; //Note : with counter the process is not synchronized. increment() method added with synchronized.
                    increment();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i< 1000; i++)
                    //++counter;
                    increment();
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


    public static void main(String[] args){

        process();
        System.out.println("Counter :"+counter);

    }
}
