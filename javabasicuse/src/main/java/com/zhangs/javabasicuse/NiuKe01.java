package com.zhangs.javabasicuse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NiuKe01 {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);//从键盘获取输入
//        System.out.println("请分别输入两个集合的长度,中间请用空格隔开");
        String str=scanner.nextLine();
        String[] sizes=str.split(" ");
        int len=sizes.length;
        int aSize=Integer.parseInt(sizes[0]);
        int bSize=Integer.parseInt(sizes[1]);
        ArrayList<Integer> aList=new ArrayList<>(aSize);
        ArrayList<Integer> bList=new ArrayList<>(bSize);

//        System.out.println("请A集合的元素,中间请用空格隔开");
        String aString=scanner.nextLine();
        String[] a=aString.split(" ");
        for (int i = 0; i <aSize ; i++) {
            aList.add(Integer.parseInt(a[i]));
        }
//        System.out.println("请B集合的元素,中间请用空格隔开");
        String bString=scanner.nextLine();
        String[] b=bString.split(" ");
        for (int i = 0; i <bSize ; i++) {
            bList.add(Integer.parseInt(b[i]));
        }

        ArrayList<Integer> total=new ArrayList<>();
        for(int i=0;i<aList.size();i++){
            if(!total.contains(aList.get(i))){
                total.add(aList.get(i));
            }
        }

        for(int i=0;i<bList.size();i++){
            if(!total.contains(bList.get(i))){
                total.add(bList.get(i));
            }
        }
        Collections.sort(total);
        for (int i = 0; i <total.size() ; i++) {
            if(i==total.size()-1){
                System.out.print(total.get(i));
            }else{
                System.out.print(total.get(i)+" ");
            }
        }
    }


}
