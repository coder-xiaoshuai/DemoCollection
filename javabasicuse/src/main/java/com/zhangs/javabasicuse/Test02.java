package com.zhangs.javabasicuse;

public class Test02 {

    public static void main(String[] args){
        int x = 0;
        int y = 0;
        int k = 0;
        for (int z = 0; z < 5; z++) {
            if ((++x > 2) && (++y > 2) && (k++ > 2))
            {
                x++;
                ++y;
                k++;
            }
        }
        System.out.println(x + "————" +y + "————" +k);

        Integer n1 = new Integer(47);
        Integer n2 = new Integer(47);
        Integer n3=47;
        Integer n4=Integer.valueOf(47);
        System.out.print(n1 == n2);
        System.out.print(n1 == 3);
        System.out.print(n3==n4);
        System.out.print(n2==n4);
        System.out.print(",");
        System.out.println(n1 != n2);

        try{
            System.out.println("执行到了try");
            System.exit(0);
        }finally {
            System.out.println("执行到了finally");
        }
    }
}
