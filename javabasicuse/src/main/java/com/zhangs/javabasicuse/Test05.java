package com.zhangs.javabasicuse;

public class Test05 {
    static boolean foo(char c)
    {
        System.out.print(c);
        return true;
    }
    public static void main( String[] argv )
    {
        int i = 0;
        for ( foo('A'); foo('B') && (i < 2); foo('C'))
        {
            i++ ;
            foo('D');
        }

        int x = 1 , y = 2 , z = 3;
        System.out.print( y+=z--/++x );
    }

    public int test(int a,int b){
        Test04 test04=new Test04();
        test04.test04method();
        try {
            return a+b;
        }
        catch (Exception e) {
            System.out.println("catch语句块");
        }
        finally{
            System.out.println("finally语句块");
        }
        return 0;
    }

    public void method222(){
    }
}
