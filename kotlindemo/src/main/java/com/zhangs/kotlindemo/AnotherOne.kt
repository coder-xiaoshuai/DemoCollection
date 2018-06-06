package com.zhangs.kotlindemo

import kotlin.properties.Delegates

class AnotherOne(one:Shangji):Shangji by one{
    val LazyA:String by lazy {
        print("输出了内容")
        "嘿嘿"
}
fun main(args:Array<String>){
    var one=Me("haha")
    AnotherOne(one).getTask()
}
}

class Watchable{
    var value:String by Delegates.observable("初始值"){property, oldValue, newValue ->
        println("旧值$oldValue->新的值:$newValue")
    }
}