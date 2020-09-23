package com.edu.nbu.cn.syn;

/**
 * AQS 基于CAS LockSupport CLH队列 的抽象同步器
 * CAS 保证AQS中的状态更新、等待状态更新、队列中节点添加操作的线程安全
 * LockSupport 用于同步等待队列中线程的阻塞、唤醒
 * CLH 用于控制等待队列中线程的唤醒
 * 为什么使用CAS 而不使用synchronized，CAS对单个node做同步操作，无需锁住整个list
 */
public class AbstractQueuedSynchronizerDemo {
    private String name;

    // JDK1.9之后的新特性: VarHandle 对一个变量的强类型引用（可理解为直接操作二进制码）,支持普通类型的原子性操作,弥补api的一些不足：
    // 1、使用Atomic包下的原子类进行间接管理，但是开销比较大，存在ABA问题
    // 2、使用原子性的FieldUpdaters，利用反射机制，开销比较大
    // 3、使用Unsafe 提供的JVM 内置函数，但是直接操作JVM会损害安全性和可移植性。
    // 例如： MethodHandles.Lookup lookUp = MethodHandles.lookup();
    // name = lookup.findVarHandle(AbstractQueuedSynchronizerDemo.class,"name",String.class);
}
