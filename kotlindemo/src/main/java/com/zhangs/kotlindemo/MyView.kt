package com.zhangs.kotlindemo

import android.content.Context
import android.util.AttributeSet
import android.view.View

class MyView : View {
    constructor(context: Context):super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

}