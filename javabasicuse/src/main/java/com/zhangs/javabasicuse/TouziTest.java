package com.zhangs.javabasicuse;

import java.util.Random;

public class TouziTest {
    private static Object lockObjectA=new Object();
    private static Object lockObjectB=new Object();
    static class ThreadA extends Thread{
        private Object object;
        private boolean isRun=true;
        public ThreadA(boolean isRun,Object object,String threadName){
            super(threadName);
            this.isRun=isRun;
            this.object=object;
        }
        @Override
        public void run() {
            super.run();
            Random random=new Random();
            while (isRun){

                try {
                    int randomNum=random.nextInt(6)+1;
                    ThreadB.random=randomNum;
                    synchronized (lockObjectB){
                        lockObjectB.notify();
                    }
                    synchronized (lockObjectA){

                        lockObjectA.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    static class ThreadB extends Thread{
        private Object object;

        private boolean isRun=true;
        private Node node;
        private static int random;
        public ThreadB(boolean isRun,Object object,String threadName){
            super(threadName);
            this.isRun=isRun;
            this.object=object;
            node=new Node();
        }


        @Override
        public void run() {
            super.run();
            while (isRun){
                System.out.println(Thread.currentThread().getName()+"---------摇色子的值是----------"+random);
                node.setFirst(node.getSecond());
                node.setSecond(node.getThird());
                node.setThird(random);
                System.out.println(Thread.currentThread().getName()+"---------node的值----------"+node.getFirst()+node.getSecond()+node.getThird());

                if(node.getFirst()==6&&node.getSecond()==6&&node.getThird()==6){
                    //都是6 退出线程
                    isRun=false;
                    System.out.println(Thread.currentThread().getName()+"---------摇色子的值是----------退出");
                    return;
                }
                synchronized (lockObjectA){
                    lockObjectA.notify();
                }
                synchronized (lockObjectB){
                    try {
                        lockObjectB.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }


    public static void main(String[] args){
        Object lockObject=new Object();
        ThreadA threadA=new ThreadA(true,lockObject,"ThreadA");
        ThreadB threadB=new ThreadB(true,lockObject,"ThreadB");
        threadA.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
    }

}
