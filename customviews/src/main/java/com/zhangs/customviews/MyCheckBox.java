package com.zhangs.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.autofill.AutofillManager;
import android.widget.CheckBox;

@SuppressLint("AppCompatCustomView")
public class MyCheckBox extends CheckBox {
    public MyCheckBox(Context context) {
        super(context);
    }

    public MyCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean checked) {

    }
}
