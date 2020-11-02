package com.edu.nbu.cn.threadpool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinPoolDemo {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static final Random random = new Random();
    static long allSum = 0L;

    static{
        for(int i=0;i<nums.length;i++){
           nums[i] = random.nextInt(100);
        }
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        long sum1 = Arrays.stream(nums).sum();
        System.out.println("sum = " + sum1 +  " - " + (System.currentTimeMillis() - start));

        System.out.println(Runtime.getRuntime().availableProcessors());
        long start1 = System.currentTimeMillis();
        ForkJoinPool pool  = new ForkJoinPool();
        AddTask myTask = new AddTask(0,nums.length);
        pool.execute(myTask);
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);
        System.in.read();
    }


        static  class AddTask extends RecursiveAction{
        int start;
        int end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if(end - start <= MAX_NUM){
                long sum = 0L;
                for(int j= start ;j <end;j++){
                    sum += nums[j];
                }
                allSum += sum;
                System.out.println("from : " + start + " to : " + end + " = " + allSum);
            }else{
                int middle = start + (end -start)/2;
                AddTask at1 = new AddTask(start, middle);
                AddTask at2 = new AddTask(middle,end);
                at1.fork();
                at2.fork();
            }
        }
    }
}
