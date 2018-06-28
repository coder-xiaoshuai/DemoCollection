package com.zhangs.baselib;

import android.content.Loader;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import java.io.File;

/**
 * 该类为图片加载框架的通用属性封装，不能耦合任何一方的框架
 */
public class LoaderOptions {

    public int placeholderResId;
    public int errorResId;
    public boolean isCenterCrop;
    public boolean isCenterInside;
    public boolean skipLocalCache;//是否缓存到本地
    public boolean skipNetCache;
    public Bitmap.Config config=Bitmap.Config.RGB_565;
    public int targetWidth;
    public int tartgetHeight;
    public float bitmapAngle;//圆角角度
    public float degrees;//旋转角度。 注意picasso针对三星等本地图片，默认旋转回0度，即正常位置。此时不需要自己rotate
    public Drawable placeholder;
    public View targetView;//targetView展示图片
    public BitmapCallBack callBack;
    public String url;
    public File file;
    public int drawableResId;
    public Uri uri;



    public LoaderOptions(String url){
        this.url=url;
    }

    public LoaderOptions(File file){
        this.file=file;
    }

    public LoaderOptions(int drawableResId){
        this.drawableResId=drawableResId;
    }

    public LoaderOptions(Uri uri){
        this.uri=uri;
    }

    public LoaderOptions centerCrop(){
        isCenterCrop=true;
        return this;
    }

    public LoaderOptions centerInside(){
        isCenterInside=true;
        return this;
    }

    public LoaderOptions config(Bitmap.Config config){
        this.config=config;
        return this;
    }

    public LoaderOptions resize(int tartgetWidth,int tartgetHeight){
        this.targetWidth=tartgetWidth;
        this.tartgetHeight=tartgetHeight;
        return this;
    }

    /**
     * 圆角
     * @param bitmapAngle
     * @return
     */
    public LoaderOptions angle(float bitmapAngle){
        this.bitmapAngle=bitmapAngle;
        return this;
    }

    public LoaderOptions skipLocalCache(boolean skipLocalCache){
        this.skipLocalCache=skipLocalCache;
        return this;
    }

    public LoaderOptions skipNetCache(boolean skipNetCache){
        this.skipNetCache=skipNetCache;
        return this;
    }

    public LoaderOptions rotate(float degrees){
        this.degrees=degrees;
        return this;
    }
}
