package com.zhangs.javabasicuse;

import java.util.ArrayList;

public class FanXingTest {
    public static void printColl(ArrayList<?> arrayList){
        for (int i=0;i<50;i++){
            if(arrayList.get(i) instanceof String){

            }
        }
    }

    public static <T> void printColl2(ArrayList<T> arrayList){
        for (int i=0;i<50;i++){
            T t=arrayList.get(i);
            if(t instanceof String){

            }
        }
    }

}
