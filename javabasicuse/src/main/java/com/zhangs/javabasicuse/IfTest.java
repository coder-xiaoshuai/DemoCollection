package com.zhangs.javabasicuse;

public class IfTest {
    public static int c(int a) {
        int c = 1;
        if (a > 0)
            if (a > 3)
                c = 2;
            else
                c = 3;
        else
            c = 4;
        return c;
    }

    public static int b(){
        int a='2';
        return a;
    }
    public static void main(String[] args){
        System.out.println("c的值"+c(3));
        System.out.println("b的值"+b());
    }
}
