package com.zhangs.javabasicuse;

/**
 * 排序工具类
 */
public class SortUtils {

    /**
     * 冒泡排序
     * @param array
     */
    public static void bubbleSort(int[] array){
        int len=array!=null?array.length:0;
        for(int i=0;i<len-1;i++){
            for(int j=0;j<len-i-1;j++){
                if(array[j]>array[j+1]){
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }

        printArray(array);
    }

    /**
     * 插入排序
     * @param array
     */
    public static void insertSort(int[] array){
        int len=array.length;
        int temp;
        for (int i=1;i<len;i++){
            temp=array[i];
            for(int j=i;j<1;j--){
                if(array[j]>temp){
                    array[j+1]=array[j];
                    array[j]=temp;
                }
            }
        }
        printArray(array);
    }

    /**
     * 打印数组
     * @param array
     */
    public static void printArray(int[] array){
        int len=array.length;
        for (int i = 0; i < len; i++) {
            System.out.print("___"+array[i]+"___");
        }
    }
    public static void main(String[] args){
        int[] array=new int[]{15,5,6,12,8,16,18};
        bubbleSort(array);
        System.out.println();
        insertSort(array);
    }
}
