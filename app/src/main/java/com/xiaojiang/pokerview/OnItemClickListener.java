package com.xiaojiang.pokerview;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by guoxiaojiang on 2017/6/5.
 */

public interface OnItemClickListener<T> {
    void onItemClick(ViewGroup var1, View var2, T var3, int var4);

    boolean onItemLongClick(ViewGroup var1, View var2, T var3, int var4);
}