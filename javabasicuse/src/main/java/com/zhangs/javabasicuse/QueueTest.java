package com.zhangs.javabasicuse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class QueueTest {
    /**
     * 无界队列
     */
    public static void unBoundedQueue(){
        //----------基于链表的阻塞队列-------
        //又称无界队列
        LinkedBlockingDeque<String> linkedBlockingDeque=new LinkedBlockingDeque<>();
        linkedBlockingDeque.add("a");
        linkedBlockingDeque.add("b");
        linkedBlockingDeque.add("c");
        linkedBlockingDeque.add("d");
        linkedBlockingDeque.add("e");

        for (String str:linkedBlockingDeque){
            System.out.println(str);
        }
        //分割线
        System.out.print("-----------------------");
        List<String> list=new ArrayList<>();
        /**
         * drainTo从队列中取前n个元素到集合里，该方法可以批量的从队列中取元素，拿走的数据，队列中就没有了
         */
        linkedBlockingDeque.drainTo(list,3);
        for (String str:list) {
            System.out.println(str);
        }
        //分割线
        System.out.print("-----------------------");

        for (String str:linkedBlockingDeque) {
            System.out.print(str);
        }
    }

    /**
     * 有界队列
     */
    public static void boundedQueue(){
        //---------基于数组的阻塞队列-------
        //又称有界队列，使用很频繁
        ArrayBlockingQueue<String> arrayBlockingQueue=new ArrayBlockingQueue<>(3);
        try {
            /**
             * offer(e,timeout,unit) 阻塞着加入
             * 参数：
             * e:元素
             * timeout:延时
             * unit:时间单位
             *
             * 下面表示添加元素a，如果2秒之后加进去就返回true，否则，返回false
             */
            arrayBlockingQueue.offer("a",2, TimeUnit.SECONDS);
            arrayBlockingQueue.add("b");
            arrayBlockingQueue.add("c");
            arrayBlockingQueue.add("d");
            arrayBlockingQueue.add("e");
            //返回false，因为5个已经满了，2s后加不进去，2秒后会返回false，不回抛出异常
            System.out.print(arrayBlockingQueue.offer("f",2,TimeUnit.SECONDS));
            //5个已经满了，再用add、put等添加会抛出Queue full异常
            arrayBlockingQueue.add("g");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
