package com.edu.nbu.cn.utils;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterDemo {



    public static void main(String[] args) {
        //qps 10，每秒请求
        RateLimiter limiter = RateLimiter.create(10);
        while(true){
            long start = System.currentTimeMillis();
            limiter.acquire();
            System.out.println(System.currentTimeMillis() - start);
        }
    }


}
