package com.edu.nbu.cn.problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProblemAndConsumer2 {
    static class MyCollection{
        private final LinkedList<String> list = new LinkedList<>();
        private int count = 0;

        private Lock lock = new ReentrantLock();

        //等待队列,可以精确指定那些线程可执行;AQS内部实现
        private Condition producer = lock.newCondition();
        private Condition consumer = lock.newCondition();

        public void put(String t) {
            try{
                lock.lock();
                while (list.size() == 10){
                    //当前线程加入等待队列,LockSupport.park()
                    producer.await();
                }
                list.add(t);
                ++ count;
                // 唤醒所有等待队列的所有线程，LockSupport.unpark()
                consumer.signalAll();
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }finally {
                lock.unlock();
            }

        }


        public String get(){
            try{
                lock.lock();
                while(list.size() == 0){
                    consumer.await();
                }
                String s = list.removeFirst();
                --count;
                producer.signalAll();
                return s;

            }catch (InterruptedException ex){
                ex.printStackTrace();
            }finally {
                lock.unlock();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        MyCollection mc = new MyCollection();

        List<Thread> producers = new ArrayList<>();
        for (int i = 0; i <2 ; i++) {
            producers.add(new Thread(() ->{
                for (int j = 0; j < 25; j++) {
                    mc.put(Thread.currentThread().getName() + " produce  " + j);
                }
            },"producer" + i));
        }

        List<Thread> consumers = new ArrayList<>();
        for(int i=0;i<10;i++){
            consumers.add(new Thread(() ->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName() + " consume  " + mc.get());
                }

            },"consumer" + i));
        }

        producers.forEach(t -> t.start());
        consumers.forEach(t -> t.start());
    }
}
