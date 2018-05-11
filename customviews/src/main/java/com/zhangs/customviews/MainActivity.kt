package com.zhangs.customviews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*;
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {
    var start=10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_reset.setOnClickListener{
            myview.reset()
            start=10
        }

        btn_add.setOnClickListener{
            start+=1
            myview.setCurrentPercent(start)
        }

        btn_start.setOnClickListener{
            myview.startRotation()
        }
    }
    var downY=0f
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN->{
                downY=ev.rawY
            }
            MotionEvent.ACTION_MOVE->{
                var deltaY=ev.rawY?.minus(downY!!)
                myview.setCurrentPercent(deltaY?.div(10)?.absoluteValue?.toInt()?:0)
            }
        }



        return super.dispatchTouchEvent(ev)
    }
}
