package com.edu.nbu.cn;

import org.openjdk.jmh.annotations.*;

@State(Scope.Thread)
public class JmhTest {


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Threads(4)
    @Warmup
    @Fork(4)
    public void testJmh(){
        System.out.println("jmh");
    }
}
