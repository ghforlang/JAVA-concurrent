package com.edu.nbu.cn.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixedThreadPoolDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start  = System.currentTimeMillis();
        List<Integer> result = getPrime(1,200000);
        long end = System.currentTimeMillis();
        System.out.println("串行耗时:" + (end - start));


        //使用线程池
        long start1 = System.currentTimeMillis();
        final int cpuCoreNum = 4;
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

        MyTask task1 = new MyTask(1,50000);
        MyTask task2 = new MyTask(50000,100000);
        MyTask task3 = new MyTask(100000,150000);
        MyTask task4 = new MyTask(150000,200000);

        Future<List<Integer>> future1 = service.submit(task1);
        Future<List<Integer>> future2 = service.submit(task2);
        Future<List<Integer>> future3 = service.submit(task3);
        Future<List<Integer>> future4 = service.submit(task4);

        List<Integer> fResult1 = future1.get();
        List<Integer> fResult2 = future2.get();
        List<Integer> fResult3 = future3.get();
        List<Integer> fResult4 = future4.get();
        List<Integer> result2 = new ArrayList<>();
//        result2.addAll(fResult1);
//        result2.addAll(fResult2);
//        result2.addAll(fResult3);
//        result2.addAll(fResult4);
        long end1 = System.currentTimeMillis();
        System.out.println("并行耗时：" + (end1 - start1));
        service.shutdownNow();
    }


    static class MyTask implements Callable<List<Integer>> {
        private int start;
        private int end;

        public MyTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() throws Exception {
            return getPrime(start,end);
        }
    }


    private static List<Integer> getPrime(int start,int end){
        List<Integer> primeList = new ArrayList<>();
        for(int j =start;j < end; j++){
            if(j %2 == 0){
                primeList.add(j);
            }
        }
        return primeList;
    }

}
