package com.zhangs.kotlindemo

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.nio.file.Paths

class SecondActivity:AppCompatActivity(){

    var text:String?=null
    get() {return field}
    set(value) {
        text=value
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HelloWorld().customMethod()
    }

    fun applyTest(){
        var task=Runnable(){ print("haha")}
        Thread(task).apply { isDaemon=true }.start()
    }

    @TargetApi(Build.VERSION_CODES.O)
            /**
     * let和apply类似，唯一的不同是返回值，let返回的不是原来的对象，而是闭包里面的值
     */
    fun letTest(){
        val outputPath=Paths.get("/user/home").let {
            val path=it.resolve("output")
            path.toFile().createNewFile()
            path
        }
    }

    fun withTest(){
        var str="hahahahha"
        with(str){
            str.length
            str.reversed()
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
            /**
     * run 是 with和let 的组合
     */
    fun runTest(){
        val outputPaths=Paths.get("/user").run{
            val path=resolve("output")
            path.toFile().createNewFile()
            path
        }
    }
}