package com.zhangs.javabasicuse;

import android.text.method.MetaKeyKeyListener;

import java.util.ArrayList;

public class Test03 {

    static class Father{
        public Father(){
            System.out.println("father_noname");
        }
        public Father(String name){
            System.out.println("father"+name);
        }
    }

    static class Son extends Father{
        public Son(String name){
            super(name);
            System.out.println("son"+name);
        }
    }

    public static void main(String[] args){
        new Son("erzi");

        ArrayList<Object> objects=new ArrayList<>();
        ArrayList<Number> numbers=new ArrayList<>();
        objects.add(456);
    }

    static class MyClass extends Father{
        void method(){
            String str="hello world";
            str+='a';
        }

        void method2() throws Exception{
            throw new Exception("发生异常了");


        }

        void main(){}
        void main(int  m){

        }

    }
    abstract class HHHH{
        private int num=2;
    }

    abstract interface HHKJHKJ{

        int k=10;
    }

}
