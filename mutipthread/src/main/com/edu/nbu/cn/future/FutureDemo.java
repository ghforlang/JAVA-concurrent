package com.edu.nbu.cn.future;

import java.util.concurrent.*;

public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask(() ->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 128;
        });

        new Thread(task).start();
        //阻塞
        System.out.println(task.get());

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> future = service.submit(() ->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
        System.out.println(future.get());
        System.out.println(future.isDone());
        service.shutdownNow();
    }
}
