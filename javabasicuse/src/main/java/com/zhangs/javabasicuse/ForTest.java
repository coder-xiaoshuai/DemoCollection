package com.zhangs.javabasicuse;

public class ForTest {

    public static void main(String[] args){
        boolean flag=true;
        for(;;){
            System.out.println("进入了循环");
            if(flag){
                break;
            }
            System.out.println("判断结束");
        }

        System.out.println("循环结束");
    }
}
