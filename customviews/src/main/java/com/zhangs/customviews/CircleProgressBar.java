package com.zhangs.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class CircleProgressBar extends View {
    private Paint mPaint;//用于绘制的画笔
    private static final int DEFAULT_DIMENSION=50;//默认尺寸

    private static final int DEFAULT_START_COLOR= Color.YELLOW;//开始颜色
    private static final int DEFAULT_END_COLOR= Color.RED;//结束颜色
    private int mStartColor;
    private int mEndColor;

    private static final int DEFAULT_WIDTH_LINE=10;//默认宽度
    private int mLineWidth;

    private int currentPercent;//当前百分比
    private RectF mRectF;

    private RotateAnimation rotateAnimation;
    private boolean isRotating=false;//正在旋转
    public CircleProgressBar(Context context) {
        super(context);
        init();
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.CircleProgressBar);
        mStartColor=array.getColor(R.styleable.CircleProgressBar_start_color,DEFAULT_START_COLOR);
        mEndColor=array.getColor(R.styleable.CircleProgressBar_end_color,DEFAULT_END_COLOR);
        mLineWidth= (int) array.getDimension(R.styleable.CircleProgressBar_line_width,DEFAULT_WIDTH_LINE);
        currentPercent=array.getInt(R.styleable.CircleProgressBar_current_percent,0);
        array.recycle();
        init();
    }

    private void init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mLineWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        int radius=getMeasuredHeight()/2;
        SweepGradient gradient=new SweepGradient(radius,radius,new int[]{mStartColor,mEndColor},null);
        mPaint.setShader(gradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mRectF==null){
            mRectF=new RectF(0+mLineWidth/2,0+mLineWidth/2,getMeasuredWidth()-mLineWidth/2,getMeasuredHeight()-mLineWidth/2);
        }
        //画弧线
        canvas.drawArc(mRectF,270f,360*(currentPercent/100f),false,mPaint);
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

    public void setCurrentPercent(int percent){
        currentPercent=percent;
        invalidate();
    }

    public void reset(){
        currentPercent=0;
        isRotating=false;
        if(rotateAnimation!=null){
            rotateAnimation.cancel();
        }
        invalidate();
    }

    /**
     * 开启旋转
     */
    public void startRotation(){
        if(currentPercent==100){
            if(rotateAnimation==null){
                rotateAnimation=new RotateAnimation(0,360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(500);
                rotateAnimation.setRepeatCount(20);
                rotateAnimation.setRepeatMode(Animation.RESTART);
                rotateAnimation.setFillAfter(false);
            }
            if(!isRotating){
                this.setAnimation(rotateAnimation);
                rotateAnimation.start();
            }
            isRotating=true;
        }
    }


    public void stopRotation(){
        isRotating=false;
        if(rotateAnimation!=null){
            rotateAnimation.cancel();
        }
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        isRotating=false;
    }
}
