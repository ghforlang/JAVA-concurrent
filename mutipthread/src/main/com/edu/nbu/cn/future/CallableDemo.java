package com.edu.nbu.cn.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {


    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Callable<String> callable = () -> {
            return "callableDemo";
        };

        Callable<String> c = () ->{
           return "0";
        };

        Future<String> future = executorService.submit(callable);//异步
        System.out.println(future.get());;//阻塞

        Future<String> f = executorService.submit(c);
        System.out.println(f.get());

        executorService.shutdownNow();
    }
}
