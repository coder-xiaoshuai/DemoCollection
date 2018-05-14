package com.zhangs.javabasicuse;

import java.util.ArrayList;

public class ThreadHelloWorld {
    class MyThread extends Thread{
        private Object object;
        public MyThread(Object object,String threadName){
            super(threadName);
            this.object=object;
        }
        @Override
        public void run() {
            super.run();
            System.out.println("------hello-----"+Thread.currentThread().getName());
            try {
                synchronized (object){
                    object.wait();
                }
                System.out.println("------world-----"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args){
        ArrayList<Object> objects=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Object object=new Object();
           MyThread myThread=new ThreadHelloWorld().new MyThread(object,"MyThread"+i);
           myThread.start();
           objects.add(object);
        }
        try {
            Thread.sleep(1000);
            for (int i = 0; i <5; i++) {
                synchronized (objects.get(i)){
                    objects.get(i).notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
