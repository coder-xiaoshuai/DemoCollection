package com.zhangs.customviews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*;
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
    }
}
