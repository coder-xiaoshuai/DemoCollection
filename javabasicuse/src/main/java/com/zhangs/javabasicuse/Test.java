package com.zhangs.javabasicuse;


import java.lang.reflect.Field;

class Value{
    public int i=15;
}
public class Test{

    public static void main(String argv[]){
        Test t=new Test( );
        t.first( );
    }

    public void first( ){
        int i=5;
        Value v=new Value( );
        v.i=25;
        second(v,i);
        System.out.println(v.i);
    }

    public void second(Value v,int i){
        i = 0;
        v.i = 20;
        Value val = new Value( );
        v = val;
        System.out.println(v.i+" "+i);
    }
}

abstract class Taaa{
    protected abstract void f1();
    static final void fq(){}
}

class Foo {
    final int i=1;
    int j;
    public void doSomething() {
        System.out.println(++j + i);
    }
}