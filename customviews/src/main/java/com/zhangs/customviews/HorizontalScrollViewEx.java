package com.zhangs.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HorizontalScrollViewEx extends ViewGroup {

    private static final String TAG = "HorizontalScrollViewEx";

    private int mChildrenSize;
    private int mChildWidth;
    private int mChildIndex;

    //分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    //分别记录上次滑动的坐标(onInterceptTouchEvent)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    private Scroller mSroller;
    private VelocityTracker mVelocityTracker;

    public HorizontalScrollViewEx(Context context) {
        super(context);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (mSroller == null) {
            mSroller = new Scroller(getContext());
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft=0;
        final int childCount=getChildCount();
        mChildrenSize=childCount;
        for (int i=0;i<childCount;i++){
            View childView=getChildAt(i);
            if (childView.getVisibility()!=View.GONE){
                int childWidth=childView.getMeasuredWidth();
                mChildWidth=childWidth;
                childView.layout(childLeft,0,childLeft+childWidth,childView.getMeasuredHeight());
                childLeft+=childWidth;
            }
        }
    }

    private void smoothScrollBy(int dx,int dy){
        mSroller.startScroll(getScrollX(),0,dx,500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(mSroller.computeScrollOffset()){
            scrollTo(mSroller.getCurrX(),mSroller.getCurrY());
            postInvalidate();
        }
    }

    /**
     * 拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN://点击屏幕
                intercepted = false;
                if (!mSroller.isFinished()) {
                    mSroller.abortAnimation();
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_MOVE://滑动事件
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    //水平滑动距离大于竖直滑动距离就拦截事件
                    intercepted = true;
                } else {
                    //否则不拦截
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP://抬起事件
                intercepted = false;
                break;
            default:
                break;
        }

        return intercepted;
    }

    /**
     * 消费
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mSroller.isFinished()) {
                    mSroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                //位置校正
                if (Math.abs(xVelocity) >= 50) {
                    mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
                } else {
                    mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
                }

                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildrenSize - 1));
                int dx = mChildIndex * mChildWidth - scrollX;
                smoothScrollBy(dx,0);
                mVelocityTracker.clear();
                break;
            default:
                break;
        }

        mLastX=x;
        mLastY=y;
        return true;
    }

    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth=0;
        int measuredHeight=0;

        //获取有多少个子控件
        final int childCount=getChildCount();
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize=MeasureSpec.getSize(heightMeasureSpec);
        //认为所有的子view宽度一样
        final View childView=getChildAt(0);
        measuredWidth=childView.getMeasuredWidth()*childCount;
        measuredHeight=childView.getMeasuredHeight();
        if(childCount==0){
            setMeasuredDimension(0,0);
        }else if(widthSpecMode==MeasureSpec.AT_MOST&&heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(measuredWidth,measuredHeight);
        }else if(heightSpecMode==MeasureSpec.AT_MOST){
            measuredHeight=childView.getMeasuredHeight();
            setMeasuredDimension(widthSpecSize,measuredHeight);
        }else if(widthSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(measuredWidth,heightSpecSize);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mVelocityTracker.recycle();
    }
}
