package com.zhangs.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        method3(1,5,5,5,5,5,5,5)
        HelloWorld().addMethod()
        tv_hello.text="wojosjfkjdslafsljdl fsa"
    }

    fun method1(){
        var range1 = 1..100
        range1.step(5)
    }

    fun method2(num1:Int=1,num2:Int=100){

    }

    fun method3(y:Int,vararg x:Int){}

    fun checkNumber(start:Int,end:Int){
        fun isThrees(x:Int)=(x%3==0)
        fun isFives(x:Int)=(x%5==0)

        for (number in start..end){
            when{
                isThrees(number)->{

                }
                isFives(number)->{

                }
                isThrees(number)&&isFives(number)->{

                }
            }

        }
    }

    fun HelloWorld.addMethod()={
        print("HelloWorld 增加了一个新的方法")
    }

}
