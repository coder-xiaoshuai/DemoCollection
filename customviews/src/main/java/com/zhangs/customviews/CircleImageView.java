package com.zhangs.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class CircleImageView extends ImageView {

    private static final ScaleType SCALE_TYPE=ScaleType.CENTER_CROP;

    private static final Bitmap.Config BITMAP_CONFIG=Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION=2;

    private static final int DEFAULT_BORDER_WIDTH=0;
    private static final int DEFAULT_BORDER_COLOR= Color.BLACK;
    private static final int DEFAULT_CIRCLE_BACKGROUND_COLOR=Color.TRANSPARENT;
    private static final boolean DEFAULT_BORDER_OVERLAY=false;

    private final RectF mDrawableRect=new RectF();
    private final RectF mBorderRect=new RectF();

    private final Matrix mShareMatrix=new Matrix();
    private final Paint mBitmapPaint=new Paint();
    private final Paint mBorderPaint=new Paint();
    private final Paint mCircleBackgroundPaint=new Paint();

    private int mBorderColor=DEFAULT_BORDER_COLOR;
    private int mBoderWidth=DEFAULT_BORDER_WIDTH;
    private int mCircleBackgroundColor=DEFAULT_CIRCLE_BACKGROUND_COLOR;

    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBitmapHeight;

    private float mDrawableRadius;
    private float mBorderRadius;

    private ColorFilter mColorFilter;

    private boolean mReady;
    private boolean mSetupPending;
    private boolean mBorderOverlay;
    private boolean mDisableCircularTransfomation;


    public CircleImageView(Context context) {
        super(context);
        init();
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.CircleImageView,defStyleAttr,0);

        mBoderWidth=array.getDimensionPixelSize(R.styleable.CircleImageView_civ_border_width,DEFAULT_BORDER_WIDTH);
        mBorderColor=array.getColor(R.styleable.CircleImageView_civ_border_color,DEFAULT_BORDER_COLOR);
        mBorderOverlay=array.getBoolean(R.styleable.CircleImageView_civ_border_overlay,DEFAULT_BORDER_OVERLAY);
        //Look for deprecated civ_fill_color if civ_circle_background_color is not set
        if (array.hasValue(R.styleable.CircleImageView_civ_circle_background_color)){
            mCircleBackgroundColor= array.getColor(R.styleable.CircleImageView_civ_circle_background_color,DEFAULT_CIRCLE_BACKGROUND_COLOR);
        }else if (array.hasValue(R.styleable.CircleImageView_civ_fill_color)){
            mCircleBackgroundColor=array.getColor(R.styleable.CircleImageView_civ_fill_color,DEFAULT_CIRCLE_BACKGROUND_COLOR);
        }

        array.recycle();

        init();
    }


    private void init(){
        super.setScaleType(SCALE_TYPE);

        mReady=true;

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            //5.0以上
            setOutlineProvider(new OutlineProvider());
        }

        if (mSetupPending){
            setup();
            mSetupPending=false;
        }
    }

    @Override
    public ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if(scaleType!=SCALE_TYPE){
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.",scaleType));
        }
    }

    @Override
    public void setAdjustViewBounds(boolean adjustViewBounds) {
        if(adjustViewBounds){
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mDisableCircularTransfomation){
            super.onDraw(canvas);
            return;
        }

        if(mBitmap==null){
            return;
        }

        if(mCircleBackgroundColor!=Color.TRANSPARENT){
            canvas.drawCircle(mDrawableRect.centerX(),mDrawableRect.centerY(),mDrawableRadius,mCircleBackgroundPaint);
        }

        canvas.drawCircle(mDrawableRect.centerX(),mDrawableRect.centerY(),mDrawableRadius,mBitmapPaint);

        if(mBoderWidth>0){
            canvas.drawCircle(mBorderRect.centerX(),mDrawableRect.centerY(),mBorderRadius,mBorderPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    @Override
    public void setPaddingRelative(int start, int top, int end, int bottom) {
        super.setPaddingRelative(start, top, end, bottom);
        setup();
    }

    public int getBorderColor(){
        return mBorderColor;
    }

    public void setBorderColor(@ColorInt int borderColor){
        if(borderColor==mBorderColor){
            return;
        }
        mBorderColor=borderColor;
        mBorderPaint.setColor(mBorderColor);
        invalidate();
    }

    /**
     * 过时方法 instead by setBorderColor()
     * @param borderColorResource
     */
    @Deprecated
    public void setBorderColorResource(@ColorRes int borderColorResource){
        setBorderColor(getContext().getResources().getColor(borderColorResource));
    }


    public @ColorInt int getCircleBackgroundColor(){
        return mCircleBackgroundColor;
    }

    public void setCircleBackgroundColor(@ColorInt int circleBackgroundColor){
        if(circleBackgroundColor==mCircleBackgroundColor){
            return;
        }

        mCircleBackgroundColor=circleBackgroundColor;
        mCircleBackgroundPaint.setColor(mCircleBackgroundColor);
        invalidate();
    }

    public void setCircleBackgroundColorResource(@ColorRes int circleBackgroundColorResource){
        setCircleBackgroundColor(getContext().getResources().getColor(circleBackgroundColorResource));
    }

    /**
     * 方法过时  instead by getCircleBackgroundColor
     * @return
     */
    @Deprecated
    public int getFillColor(){
        return getCircleBackgroundColor();
    }

    /**
     * 方法过时 instead by setCircleBackgroundColor()
     * @param fillColor
     */
    @Deprecated
    public void setFillColorResource(@ColorInt int fillColor){
        setCircleBackgroundColor(fillColor);
    }

    public int getBorderWidth(){
        return mBoderWidth;
    }

    public void setBorderWidth(int borderWidth){
        if(borderWidth==mBoderWidth){
            return;
        }
        mBoderWidth=borderWidth;
        setup();
    }

    public boolean isBorderOverlay(){
        return mBorderOverlay;
    }


    public boolean isDisableCircularTransformation(){
        return mDisableCircularTransfomation;
    }

    public void setDisableCircularTransformation(boolean disableCircularTransformation){
        if(mDisableCircularTransfomation==disableCircularTransformation){
            return;
        }

        mDisableCircularTransfomation=disableCircularTransformation;


    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        initializeBitmap();
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        initializeBitmap();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        initializeBitmap();
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        initializeBitmap();
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        if (cf==mColorFilter){
            return;
        }

        mColorFilter=cf;
        applyColorFilter();
        invalidate();
    }

    @Override
    public ColorFilter getColorFilter() {
        return mColorFilter;
    }

    private void applyColorFilter(){
       if(mBitmapPaint!=null){
           mBitmapPaint.setColorFilter(mColorFilter);
       }
    }

    private void initializeBitmap() {
        if (mDisableCircularTransfomation) {
            mBitmap = null;
        } else {
            mBitmap = getBitmapFromDrawable(getDrawable());
        }
        setup();
    }
    private Bitmap getBitmapFromDrawable(Drawable drawable){
        if(drawable==null){
            return null;
        }
        if (drawable instanceof BitmapDrawable){
            return ((BitmapDrawable)drawable).getBitmap();
        }


        try {
            Bitmap bitmap;
            if(drawable instanceof ColorDrawable){
                bitmap=Bitmap.createBitmap(COLORDRAWABLE_DIMENSION,COLORDRAWABLE_DIMENSION,BITMAP_CONFIG);
            }else{
                bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),BITMAP_CONFIG);
            }
            Canvas canvas=new Canvas(bitmap);
            drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    private void setup(){
        if(!mReady){
            mSetupPending=true;
            return;
        }

        if(getWidth()==0&&getHeight()==0){
            return;
        }

        if(mBitmap==null){
            invalidate();
            return;
        }

        mBitmapShader=new BitmapShader(mBitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBoderWidth);

        mCircleBackgroundPaint.setStyle(Paint.Style.FILL);
        mCircleBackgroundPaint.setAntiAlias(true);
        mCircleBackgroundPaint.setColor(mCircleBackgroundColor);

        mBitmapHeight=mBitmap.getHeight();
        mBitmapWidth=mBitmap.getWidth();

        mBorderRect.set(calculateBounds());
        mBorderRadius=Math.min((mBorderRect.height() - mBoderWidth) / 2.0f, (mBorderRect.width() - mBoderWidth) / 2.0f);

        mDrawableRect.set(mBorderRect);
        if(!mBorderOverlay&&mBoderWidth>0){
            mDrawableRect.inset(mBoderWidth-1.0f,mBoderWidth-1.0f);
        }

        mDrawableRadius=Math.min(mDrawableRect.height()/2.0f,mDrawableRect.width()/2.0f);

        applyColorFilter();
        updateShaderMatrix();
        invalidate();
    }


    private void updateShaderMatrix(){
        float scale;
        float dx=0;
        float dy=0;

        mShareMatrix.set(null);

        if(mBitmapWidth*mDrawableRect.height()>mDrawableRect.width()*mBitmapHeight){
            scale=mDrawableRect.height()/(float)mBitmapHeight;
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f;
        } else {
            scale = mDrawableRect.width() / (float) mBitmapWidth;
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f;
        }

        mShareMatrix.setScale(scale,scale);
        mShareMatrix.postTranslate((int) (dx + 0.5f) + mDrawableRect.left, (int) (dy + 0.5f) + mDrawableRect.top);

        mBitmapShader.setLocalMatrix(mShareMatrix);
    }

    private RectF calculateBounds(){
        int availableWidth=getWidth()-getPaddingLeft()-getPaddingRight();
        int availableHeight=getHeight()-getPaddingTop()-getPaddingBottom();

        int sideLength=Math.min(availableWidth,availableHeight);

        float left =getPaddingLeft()+(availableWidth-sideLength)/2f;
        float top=getPaddingTop()+(availableHeight-sideLength)/2f;
        return new RectF(left,top,left+sideLength,top+sideLength);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private class OutlineProvider extends ViewOutlineProvider{

        @Override
        public void getOutline(View view, Outline outline) {
            Rect bounds=new Rect();
            mBorderRect.roundOut(bounds);
            outline.setRoundRect(bounds,bounds.width()/2.0f);
        }
    }
}
