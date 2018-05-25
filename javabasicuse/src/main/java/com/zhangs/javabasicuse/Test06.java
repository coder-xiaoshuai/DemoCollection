package com.zhangs.javabasicuse;

public class Test06 {
    class Father{
        public void fatherMethod(){}
    }

    class Son extends Father{
        public void sonMethod(){
//            fatherMethod();
            super.fatherMethod();
            double[] arra;
            int i = 5;
            int j = 10;
            System.out.println(i + ~j);

        }
    }
}
