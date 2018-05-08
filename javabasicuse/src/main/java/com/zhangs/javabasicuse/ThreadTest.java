package com.zhangs.javabasicuse;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest{
    public static void main(String[] args){
        Thread thread1=new MyThread("线程1");
        thread1.start();

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("------------实现Runnable------------");
            }
        });

        thread2.start();

        /*----第三种方式-----*/
        Callable<String> callable=new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("------------callable方式创建的线程----------");

                return "------------callable方式创建的线程----------";
            }
        };

        FutureTask<String> futureTask=new FutureTask<>(callable);
        Thread thread3=new Thread(futureTask);
        thread3.start();


        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(3,10,30, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(5));
        threadPoolExecutor.execute(thread1);
    }

    static class MyThread extends Thread{
        public MyThread(String threadName){
            super(threadName);
        }
        @Override
        public void run() {
            super.run();
            System.out.println("------------继承方式创建的线程------------");
        }
    }




}
