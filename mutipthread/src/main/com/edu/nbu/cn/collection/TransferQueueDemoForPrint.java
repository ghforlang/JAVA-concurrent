package com.edu.nbu.cn.collection;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 交替打印
 */
public class TransferQueueDemoForPrint {
    static int[] numbers =  {1,2,3,4,5,6,7,8,9,10};
    static String[] chars = {"a","b","c","d","e","f","g","h","i","j"};
    static LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();


    public static void main(String[] args) throws InterruptedException {
        new Thread(() ->{

            try {
                while (true){
                    System.out.println(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() ->{
            for(int i=0;i<10;i++){
                queue.put(numbers[i] + "");
                queue.put(chars[i]);
            }
        }).start();

//        new Thread(() ->{
//            try {
//                System.out.println(queue.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

    }
}
