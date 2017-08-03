package com.amisoft.ch1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ch1_1_demo_semaphore {

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0; i < 12; i++){

            service.execute(new Runnable() {
                @Override
                public void run() {
                    Download.INSTANCE.downloadDate();
                }
            });
        }

    }
}


enum Download {

    INSTANCE;

    private Semaphore semaphore = new Semaphore(5, true);

    public void downloadDate() {

        try {
            semaphore.acquire();
            download();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void download() {
        System.out.println("Downloading data from the web...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


