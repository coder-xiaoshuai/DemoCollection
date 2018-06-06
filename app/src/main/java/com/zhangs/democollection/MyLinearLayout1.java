package com.zhangs.democollection;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout1 extends LinearLayout {
    public MyLinearLayout1(Context context) {
        super(context);
    }

    public MyLinearLayout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("tag","--------dispatchTouchEvent--------"+this.getClass().getSimpleName());
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("tag","--------onTouchEvent--------"+this.getClass().getSimpleName());
        return super.onTouchEvent(event);
    }
}
