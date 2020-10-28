package com.edu.nbu.cn.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        CompletableFuture.supplyAsync(() -> {return 20.0;})
                .thenApply(value -> value.toString())
                .thenApply(str -> "price" + str)
                .thenAccept(System.out::println);

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> {return 1.0;});
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> {return 2.0;});
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() -> {return 3.0;});
        System.out.println(CompletableFuture.anyOf(futureJD,futureTB,futureTM).join().toString());
        System.out.println(CompletableFuture.allOf(futureJD,futureTB,futureTM).join());
        System.out.println(CompletableFuture.completedFuture(4.0).join().toString());
        CompletableFuture.runAsync(() ->{
            System.out.println("runAsync");
        });

    }
}
