package com.zhangs.javabasicuse;

public class Zi extends Fu {
    public static void main(String[] args) {
        Fu fu = new Zi();
        fu.show();
        fu.method();
    }
    public static void show() {
        System.out.println("子类的静态");
    }
    public void method() {
        System.out.println("子类的一般方法");
    }

}
