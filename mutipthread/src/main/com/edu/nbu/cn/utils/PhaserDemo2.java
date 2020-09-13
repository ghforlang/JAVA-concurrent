package com.edu.nbu.cn.utils;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserDemo2 {

    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser();
        phaser.bulkRegister(5);
        for(int i=0;i<5;i++){
            StudentTask task = new StudentTask(phaser);
            new Thread(task,"task" + i).start();
        }
    }

    static class MyPhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0: return studentArrived();
                case 1: return finish1stProblem();
                case 2: return finish2ndProblem();
                case 3: return finish3rdProblem();
                case 4: return finishAll();
                default: return true;
            }
        }

        private boolean studentArrived(){
            System.out.println("所有考生已到达,人数" + getRegisteredParties());
            return false;
        }

        private boolean finish1stProblem(){
            System.out.println("所有考生已完成第一道题目,人数" + getRegisteredParties());
            return false;
        }

        private boolean finish2ndProblem(){
            System.out.println("所有考生已完成第二道题目,人数" + getRegisteredParties());
            return false;
        }

        private boolean finish3rdProblem(){
            System.out.println("所有考生已完成第三道题目,人数" + getRegisteredParties());
            return false;
        }

        private boolean finishAll(){
            System.out.println("所有考生已完成所有题目,考试结束,人数" + getRegisteredParties());
            return true;
        }
    }

   static class StudentTask implements Runnable{
        private Phaser phaser;

        public StudentTask(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            arrive();
            do1stProblem();
            do2ndProblem();
            do3rdProblem();
            finish();
        }

        private void arrive(){
            milliSleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"到达考试");
            phaser.arriveAndAwaitAdvance();
        }

        private void do1stProblem(){
            milliSleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"完成第一道题目!");
            phaser.arriveAndAwaitAdvance();
        }

        private void do2ndProblem(){
            milliSleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"完成第二道题目!");
            phaser.arriveAndAwaitAdvance();
        }

        private void do3rdProblem(){
            milliSleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"完成第三道题目!");
            phaser.arriveAndAwaitAdvance();
        }

        private void finish(){
            milliSleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"完成所有题目!");
            phaser.arriveAndAwaitAdvance();
        }

        private  void milliSleep(int milliTime){
            try {
                TimeUnit.MILLISECONDS.sleep((long)milliTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
