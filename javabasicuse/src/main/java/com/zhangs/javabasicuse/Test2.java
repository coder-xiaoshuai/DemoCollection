package com.zhangs.javabasicuse;

public class Test2 {
    public void add(Byte b) {
        b = b++;
    }

    public void test() {
        Byte a = 127;
        Byte b = 127;
        add(++a);
        System.out.print("a的值" + a);
        add(b);
        System.out.print("b的值" + b);
    }

    public static void main(String[] args) {
        new Test2().test();

        byte b = (byte) (128);
        System.out.println("强制转换后b的值" + b);


        //  输出结果 a的值-128b的值127强制转换后b的值-128


        String str = new String("hello");
        if (str == "hello") {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        double d1 = -0.5;
        System.out.println("Ceil d1=" + Math.ceil(d1));
        System.out.println("floor d1=" + Math.floor(d1));

        System.out.println("method" + method(10));
    }

    protected final void me() {

    }

    public static int method(int b) {
        try {
            b += 10;
            return b;
        } catch (RuntimeException e) {
        } catch (Exception e2) {
        } finally {
            b += 10;
            return b;
        }
    }
}
