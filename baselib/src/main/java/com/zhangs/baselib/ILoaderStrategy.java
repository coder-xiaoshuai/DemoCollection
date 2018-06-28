package com.zhangs.baselib;

public interface ILoaderStrategy {

    void loadImage(LoaderOptions options);

    void clearMemoryCache();

    void clearDiskCache();
}
