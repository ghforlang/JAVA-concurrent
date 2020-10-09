package com.edu.nbu.cn.threadpool;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用场景 ：
 * 1、分布式跟踪系统
 * 2、应用容器或上层框架跨应用代码给下层SDK传递信息
 * 3、日志收集记录系统上下文
 */
public class TtlExecutorsDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorService e = TtlExecutors.getTtlExecutorService(executorService);
        TransmittableThreadLocal<String> parent = new TransmittableThreadLocal<>();
        
    }
}
