package com.zhangs.javabasicuse;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TouziTest2 {
    private static int tnum = 1;// 线程编号,Thread Number

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition redCon = lock.newCondition();
    private static Condition greenCon = lock.newCondition();

    public static void main(String[] args) {
        new Thread(new RedThread(), "red light").start();
        new Thread(new GreenThread(), "green light").start();
    }


    static class RedThread implements Runnable {

        @Override
        public void run() {
            Random random=new Random();
            while (true) {
                try {
                    lock.lock();
                    while (tnum != 1) {// 判断是否该自己执行了[采用while不是if是为了防止死锁]
                        redCon.await();
                    }

                    int randomNum=random.nextInt(6)+1;
                    GreenThread.random=randomNum;
                    TimeUnit.MICROSECONDS.sleep(10);// 停留时间，便于从控制台观看
                    tnum = 2;
                    greenCon.signal();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class GreenThread implements Runnable {
        private Node node=new Node();
        private static int random;
        @Override
        public void run() {

            while (true) {
                try {
                    lock.lock();
                    while (tnum != 2) {
                        greenCon.await();
                    }

                    System.out.println(Thread.currentThread().getName()+"---------摇色子的值是----------"+random);
                    node.setFirst(node.getSecond());
                    node.setSecond(node.getThird());
                    node.setThird(random);
                    TimeUnit.MICROSECONDS.sleep(10);// 停留时间，便于从控制台观看
                    if(node.getFirst()==6&&node.getSecond()==6&&node.getThird()==6){
                        //都是6 退出线程
                        System.out.println(Thread.currentThread().getName()+"---------摇色子的值是----------退出");
                        return;
                    }
                    tnum = 1;
                    redCon.signal();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

    }
}
