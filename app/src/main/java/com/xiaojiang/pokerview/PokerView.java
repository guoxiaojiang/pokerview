package com.xiaojiang.pokerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;

import com.xiaojiang.pokerview.helper.PokerItemCallback;

/**
 * Created by guoxiaojiang on 2017/6/8.
 */

public class PokerView extends RecyclerView {

    private PokerLayoutManager mPokerLayoutManager;

    public PokerView(Context context) {
        super(context);
        init();
    }

    public PokerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PokerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setPokerLayoutManager();
        ItemTouchHelper.Callback callback = new PokerItemCallback(this, mPokerLayoutManager);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(this);
    }


    private void setPokerLayoutManager() {
        setLayoutManager(mPokerLayoutManager = new PokerLayoutManager());
    }

    public PokerLayoutManager getPokerLayoutManager() {
        return mPokerLayoutManager;
    }

    public void toPrev() {
        mPokerLayoutManager.toLeftWithAnim();
    }

    public void toNext() {
        mPokerLayoutManager.toRightWithAnim();
    }

}
