package com.xiaojiang.pokerview.helper;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.xiaojiang.pokerview.PokerLayoutManager;

/**
 * Created by guoxiaojiang on 2017/6/8.
 */

public class PokerItemCallback extends ItemTouchHelper.SimpleCallback {

    protected RecyclerView mRv;
    protected PokerLayoutManager mPokerLayoutManager;

    public PokerItemCallback(RecyclerView rv, PokerLayoutManager pokerLayoutManager) {
        this(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                rv, pokerLayoutManager);
    }

    public PokerItemCallback(int dragDirs, int swipeDirs
            , RecyclerView rv, PokerLayoutManager pokerLayoutManager) {
        super(dragDirs, swipeDirs);
        mRv = rv;
        mPokerLayoutManager = pokerLayoutManager;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//        Log.e("swipecard", "onSwiped() called with: viewHolder = [" + viewHolder + "], direction = [" + direction + "]");
        Object itemViewTag = viewHolder.itemView.getTag();
        if (itemViewTag != null) {
            PokerLayoutManager.ViewHolder pokerViewHolder = (PokerLayoutManager.ViewHolder) itemViewTag;
            if (pokerViewHolder != null) {
                int tagValue = pokerViewHolder.factor;
                if (tagValue != 0) {
                    return;
                }
            }
        }
        if (mPokerLayoutManager != null) {
            if (direction == ItemTouchHelper.RIGHT) {
                //手指往右滑，要显示左边的
                mPokerLayoutManager.toLeft();
                mRv.getAdapter().notifyDataSetChanged();
            } else if (direction == ItemTouchHelper.LEFT){
                //手指往左滑，要显示右边的
                mPokerLayoutManager.toRight();
                mRv.getAdapter().notifyDataSetChanged();
            }

        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        Log.d("swipecard", "onChildDraw, dX:" + dX + "; isCurrentlyActive:" + isCurrentlyActive);
        int childCount = recyclerView.getChildCount();
        if (mPokerLayoutManager.getCurrentPosition() >= childCount-1 && dX < 0.0f) {
            return;
        }
        if (mPokerLayoutManager.getCurrentPosition() <= 0 && dX > 0.0f) {
            return;
        }

        float factor = dX / 300.0f;
        if (factor > 1) {
            factor = 1;
        }
        if (factor < -1) {
            factor = -1;
        }
        float dfX = PokerLayoutManager.TRANS_Y_GAP * factor;
        super.onChildDraw(c, recyclerView, viewHolder, dfX, dY, actionState, isCurrentlyActive);
        //对每个ChildView进行缩放 位移
        factor = Math.abs(factor);
        int translationFactor = 0;
        if (dfX > 0.0f) {
            translationFactor = 1;
        } else if (dfX < 0.0f){
            translationFactor = -1;
        }
        for(int i = 0; i < childCount; i++) {
            View view = recyclerView.getChildAt(i);
            Object tag = view.getTag();
            PokerLayoutManager.ViewHolder holder = (PokerLayoutManager.ViewHolder) tag;
            int tagValue = holder.factor;
            if (tagValue != 0) {
                view.setTranslationX(PokerLayoutManager.TRANS_Y_GAP * tagValue + (PokerLayoutManager.TRANS_Y_GAP * (factor/(Math.abs(tagValue)+1))) * translationFactor);
            }
        }
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Object itemViewTag = viewHolder.itemView.getTag();
        if (itemViewTag != null) {
            PokerLayoutManager.ViewHolder pokerViewHolder = (PokerLayoutManager.ViewHolder) itemViewTag;
            if (pokerViewHolder != null) {
                int tagValue = pokerViewHolder.factor;
                if (tagValue != 0) {
                    //只响应最上面的Item的滑动
                    return ItemTouchHelper.ACTION_STATE_IDLE;
                }
            }
        }
        return super.getMovementFlags(recyclerView, viewHolder);
    }
}
