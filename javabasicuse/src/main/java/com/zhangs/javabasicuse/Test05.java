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

        int m = 5;
        int j = 10;
        System.out.println(m + ~j);
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

    public int x(){
        char ch='a';
        int j=1;
        int x=20;
        x%=4;
        return (int)ch;
    }

    class Car extends Vehicle
    {
//        public static void main (String[] args)
//        {
//            new  Car(). run();
//        }
        private final void run()
        {
            System. out. println ("Car");
        }
    }
    class Vehicle
    {
        private final void run()
        {
            System. out. println("Vehicle");
        }
    }

}
