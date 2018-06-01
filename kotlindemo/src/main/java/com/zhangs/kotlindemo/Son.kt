package com.zhangs.kotlindemo

class Son(name:String,age:Int,sex:Boolean):Father(name,age){
    constructor(name:String):this(name,25,false)
}