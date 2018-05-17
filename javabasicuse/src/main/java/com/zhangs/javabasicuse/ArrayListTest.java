package com.zhangs.javabasicuse;

import java.util.ArrayList;

public class ArrayListTest {

    public static void main(String[]  args){
        ArrayList<Integer> list=new ArrayList(3);
        for (int i = 0; i <10 ; i++) {
            list.add(i);
        }
        for (Integer num : list) {
            System.out.println(num);
        }
    }

    protected class inner{}
}
