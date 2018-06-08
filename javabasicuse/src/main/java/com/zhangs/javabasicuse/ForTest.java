package com.zhangs.javabasicuse;

public class ForTest {

    public static void main(String[] args){
        boolean flag=true;
        for(;;){
            System.out.println("进入了循环");
            if(flag){
                return;
            }
            System.out.println("判断结束");
        }

    }
}
