package com.zhangs.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View{

    private int mColor= Color.RED;
    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int DEFAULT_DIMENSION=200;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.CircleView);
        mColor=array.getColor(R.styleable.CircleView_circle_color,Color.RED);
        array.recycle();
        init();
    }

    private void init(){
        mPaint.setColor(mColor);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft=getPaddingLeft();
        int paddingRight=getPaddingRight();
        int paddingTop=getPaddingLeft();
        int paddingBottom=getPaddingLeft();

        int width=getWidth()-paddingLeft-paddingRight;
        int height=getHeight()-paddingTop-paddingBottom;
        int radius=Math.min(width,height)/2;
        canvas.drawCircle(paddingLeft+width/2,paddingTop+height/2,radius,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize=MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode==MeasureSpec.AT_MOST&&heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(DEFAULT_DIMENSION,DEFAULT_DIMENSION);
        }else if(widthSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(DEFAULT_DIMENSION,heightSpecSize);
        }else if(heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,DEFAULT_DIMENSION);
        }
    }
}
