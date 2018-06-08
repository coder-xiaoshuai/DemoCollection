package com.zhangs.democollection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_cb_choice.setOnCheckedChangeListener{buttonView, isChecked ->
            if(!buttonView.isPressed){
                Log.i("tag","------aaaaaaaaaa不做处理")
            }else{
                Log.i("tag","------aaaaaaaaaa进行处理")
            }
        }

        main_btn_chanage.setOnClickListener{v: View? ->
            main_cb_choice.isChecked = !main_cb_choice.isChecked
            //下面的写法不推荐  冗余的if状态
//            var flag=false
//            if(flag){
//                flag=false
//            }else{
//                flag=true
//            }
        }

}

//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        Log.i("tag","--------dispatchTouchEvent---------"+this@MainActivity.javaClass.simpleName)
//        return super.dispatchTouchEvent(ev)
//    }
//
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        Log.i("tag","--------onTouchEvent---------"+this@MainActivity.javaClass.simpleName)
//        return super.onTouchEvent(event)
//    }
}
