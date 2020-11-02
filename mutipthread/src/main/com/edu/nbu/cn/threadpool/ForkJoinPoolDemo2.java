package com.edu.nbu.cn.threadpool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolDemo2 {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static final Random random = new Random();

    static{
        for(int i=0;i<nums.length;i++){
            nums[i] = random.nextInt(100);
        }
    }

    public static void main(String[] args) throws IOException {
        long sum = Arrays.stream(nums).sum();
        System.out.println("sum = " + sum);

        ForkJoinPool fjp  = new ForkJoinPool();
        AddTask task = new AddTask(0,nums.length);
        long result = fjp.invoke(task);
        System.out.println("result = " + result);
        System.in.read();
    }

    static class AddTask extends RecursiveTask<Long> {
        int start;
        int end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if(end - start <= MAX_NUM){
                long sum = 0L;
                for(int i=start;i<end;i++){
                    sum += nums[i];
                }
                return sum;
            }else{
                int middle = start + (end -start)/2;
                AddTask at1 = new AddTask(start, middle);
                AddTask at2 = new AddTask(middle,end);
                at1.fork();
                at2.fork();
                return at1.join() + at2.join();
            }
        }
    }
}
