package com.zhangs.javabasicuse;

public class Test2 {
    public void add(Byte b)
    {
        b = b++;
    }
    public void test()
    {
        Byte a = 127;
        Byte b = 127;
        add(++a);
        System.out.print("a的值"+a);
        add(b);
        System.out.print( "b的值"+b);
    }

    public static void main(String[] args){
        new Test2().test();

        byte b=(byte) (128);
        System.out.println("强制转换后b的值"+b);


        //  输出结果 a的值-128b的值127强制转换后b的值-128
    }
}
