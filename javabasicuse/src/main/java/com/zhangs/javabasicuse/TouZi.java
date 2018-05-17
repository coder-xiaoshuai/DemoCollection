package com.zhangs.javabasicuse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TouZi {
    public static void main(String[] args) {
        Runnable runnable1 = new A();
        Thread thread1 = new Thread(runnable1, "线程A");
        thread1.start();
        Runnable runnable2 = new B();
        Thread thread2 = new Thread(runnable2, "线程B");
        thread2.start();
    }

    static int touZi = 0;
    static Object object = new Object();
    static int tum = 1;
    static int touZiSize = 2;
    static List list = new ArrayList(touZiSize);

    static class A implements Runnable {
        boolean isRun = true;

        @Override
        public void run() {
            Random random = new Random();
            synchronized (object) {
                while (isRun) {
                    if (tum == 1) {
                        object.notify();
                        touZi = random.nextInt(6) + 1;
                        System.out.println("A,touzi:" + touZi);
                        if (touZi == 6) {
                            list.add(touZi);
                        } else {
                            list.clear();
                        }
                        if (list.size() >= touZiSize) {
                            isRun = false;
                        }
                        tum = 2;
                        try {
                            Thread.sleep(10);
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class B implements Runnable {
        boolean isRun = true;

        @Override
        public void run() {
            synchronized (object) {
                while (isRun) {
                    if (tum == 2) {
                        object.notify();
                        System.out.println("B,touzi:" + touZi);
                        System.out.println("B,-----------touzi:" + touZi);
                        if (list.size() >= touZiSize) {
                            System.out.println(touZiSize + ",连6");

                            isRun = false;
                        }
                        tum = 1;
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }


}
