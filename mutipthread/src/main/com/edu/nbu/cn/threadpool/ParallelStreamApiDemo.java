package com.edu.nbu.cn.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParallelStreamApiDemo {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();

        for(int i=0;i<10000;i++){
            nums.add(r.nextInt(100));
        }

        long start0 = System.currentTimeMillis();
        for(Integer value : nums){
            isEqual(value);
        }
        long end0 = System.currentTimeMillis();
        System.out.println("timeCost = " + (end0 - start0));

        long start = System.currentTimeMillis();
        nums.parallelStream().parallel().forEach(v -> isEqual(v));
        long end = System.currentTimeMillis();
        System.out.println("timeCost1 = " + (end - start));
    }

    public static boolean isEqual(Integer num){
        return num %2 ==0;
    }
}
