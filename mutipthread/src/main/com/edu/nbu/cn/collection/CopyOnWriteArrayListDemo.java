package com.edu.nbu.cn.collection;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制
 */
public class CopyOnWriteArrayListDemo {

    
    public static void main(String[] args) {
        List<String> lists = new CopyOnWriteArrayList();
        Random r = new Random();
        Thread[] threads = new Thread[100];
        
        for(int i=0;i<100;i++){ 
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <10000 ; j++) {
                        lists.add("a" + r.nextInt(10000));
                    }
                }
            };
            threads[i] = new Thread(run);
        }

        //测试执行时间

    }
}
