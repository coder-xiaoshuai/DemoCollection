package com.zhangs.javabasicuse;

public class Test04 {

    static{
        int x=5;
    }
    static int x,y;
    public static void main(String args[]){
        x--;
        myMethod( );
        System.out.println(x+y+ ++x);
    }
    public static void myMethod( ){
        y=x++ + ++x;
        String a="";
    }
    protected  void test04method(){}
    private void test04method2(){}

    protected static void hhhha(){}
}
