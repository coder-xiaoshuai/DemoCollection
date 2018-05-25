package com.zhangs.javabasicuse;

public class ExtendsTest {
    static class A{
        public A(){
            System.out.println("A no params");
        }

        public A(String str){
            int num=10_000;
            System.out.println("A1111");
            System.out.println(num);
        }
    }

    static class B extends A{
        public B(){
            System.out.println("B no params");
        }
        public B(String string){
            this();
            System.out.println("B");
        }
    }

    public static void main(String[] args){
        B b=new B("ss");
    }


}
