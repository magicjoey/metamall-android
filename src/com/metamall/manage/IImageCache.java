package com.metamall.manage;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/3/20.
 */
public interface IImageCache {
    public void set(String key, Bitmap value);
    public Bitmap get(String key);
    public void remove(String key);
    public void recycle(String key);
    public void clear();
}