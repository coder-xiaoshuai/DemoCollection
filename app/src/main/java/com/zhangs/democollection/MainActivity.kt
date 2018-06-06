package com.zhangs.democollection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.i("tag","--------dispatchTouchEvent---------"+this@MainActivity.javaClass.simpleName)
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i("tag","--------onTouchEvent---------"+this@MainActivity.javaClass.simpleName)
        return super.onTouchEvent(event)
    }
}
