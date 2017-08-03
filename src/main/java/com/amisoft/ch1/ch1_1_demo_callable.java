package com.amisoft.ch1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ch1_1_demo_callable {

   public static void main(String[] args){

       ExecutorService service = Executors.newCachedThreadPool();
       List<Future<String>> list = new ArrayList<Future<String>>();

       for(int i=0; i < 10;i++){
          Future<String> future = service.submit(new Processor(i+1));
          list.add(future);
       }

       for(Future<String> future : list){
           try {
               System.out.println(future.get());
           } catch (InterruptedException e) {
               e.printStackTrace();
           } catch (ExecutionException e) {
               e.printStackTrace();
           }
       }

   }


}

class Processor implements Callable<String> {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {

        TimeUnit.MILLISECONDS.sleep(1000);

        return "id = "+id;
    }
}
