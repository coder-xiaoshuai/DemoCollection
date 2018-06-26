package com.zhangs.javabasicuse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {


    public static void main(String[] args) {
        Thread t1 = new MyThread("t1",1);

        Thread t2 = new MyThread("t2",2);

        Thread t3 = new MyThread("t3",3);

        Thread t4 = new MyThread("t4",4);

        Thread t5 = new MyThread("t5",5);

        //创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        //关闭线程池
        pool.shutdown();
        /*
          * 上面代码输出如下：
          * pool-1-thread-1正在执行....
            pool-1-thread-1正在执行....
            pool-1-thread-1正在执行....
            pool-1-thread-1正在执行....
            pool-1-thread-1正在执行....
        * */

        //创建一个可重用固定线程数的线程池
        ExecutorService pool2= Executors.newFixedThreadPool(3);
        pool2.execute(t1);
        pool2.execute(t2);
        pool2.execute(t3);
        pool2.execute(t4);
        pool2.execute(t5);
        //关闭线程池
        pool2.shutdown();

        ExecutorService pool3= Executors.newCachedThreadPool();

        ExecutorService pool4= Executors.newScheduledThreadPool(2);

        ThreadPoolExecutor pool5=new ThreadPoolExecutor(5,10,30, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
    }

    static class MyThread extends Thread {
        private int num;
        public MyThread(String threadName) {
            super(threadName);
        }
        public MyThread(String threadName,int num) {
            super(threadName);
            this.num=num;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
//        super.run();
            System.out.println(Thread.currentThread().getName() +"num,"+num+ "正在执行....");
        }
    }
}
