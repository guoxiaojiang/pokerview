package com.xiaojiang.pokerview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

/**
 * Created by guoxiaojiang on 17/6/5.
 */
public class PokerLayoutManager extends RecyclerView.LayoutManager implements Animator.AnimatorListener {

    private static final String TAG = "PokerLayoutManager";

    private static float SCALE_GAP = 0.04f;
    public static int TRANS_Y_GAP = 20;
    public static int MAX_SHOW_PER_SIDE = 3;

    private int mPosition;

    private AnimatorSet mAnimatorSet;
    private ObjectAnimator mAnimatorAlpha;

    private boolean isToLeft;

    private OnSelectedListener mOnSelectedListener;

    public PokerLayoutManager() {
        mAnimatorAlpha = new ObjectAnimator();
        mAnimatorAlpha.setInterpolator(new LinearInterpolator());
        mAnimatorAlpha.setDuration(300);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        if (itemCount < 1) {
            return;
        }
        /**
         * 以mPosition为中心，绘左右两侧（最多各{@link MAX_SHOW_PER_SIDE}个）
         */
        for (int i = MAX_SHOW_PER_SIDE; i > 0; i--) {
            //绘左右两侧
            int left = mPosition - i;
            if (left >= 0) {
                View view = recycler.getViewForPosition(left);
                addAndLayoutview(view, i, true);
                if (mOnSelectedListener != null) {
                    mOnSelectedListener.onUnSelected(view);
                }
            }
            int right = mPosition + i;
            if (right < itemCount) {
                View view = recycler.getViewForPosition(right);
                addAndLayoutview(view, i, false);
                if (mOnSelectedListener != null) {
                    mOnSelectedListener.onUnSelected(view);
                }
            }
        }
        View view = recycler.getViewForPosition(mPosition);
        //绘中间
        addAndLayoutview(view, 0, false);
        if (mOnSelectedListener != null) {
            mOnSelectedListener.onSelected(view);
        }
    }

    public void notifyDataChangeWithAnims() {
        int itemCount = getItemCount();
        if (itemCount < 1) {
            return;
        }
        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
        }
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.addListener(this);
        for (int i = 0; i < itemCount; i++) {
            View view = getChildAt(i);
            if (view != null) {
                Object tag = view.getTag();
                if (tag != null) {
                    ViewHolder holder = (ViewHolder) tag;
                    int tagValue = holder.factor;
                    if (tagValue >= 0) {
                        addViewAnims(view, tagValue, false);
                    } else {
                        addViewAnims(view, tagValue * -1, true);
                    }
                }
            }
        }
        mAnimatorSet.cancel();
        mAnimatorSet.start();
    }

    public void addViewAnims(final View view, int factor, boolean left) {
        //Y方向的缩小
        float scaleValue;
        if (isToLeft) {
            if (factor > 0) {
                if (left) {
                    scaleValue = 1 - SCALE_GAP * (factor - 1);
                } else {
                    scaleValue = 1 - SCALE_GAP * (factor + 1);
                }
            } else {
                scaleValue = 1 - SCALE_GAP * (factor + 1);
            }
        } else {
            if (factor > 0) {
                if (left) {
                    scaleValue = 1 - SCALE_GAP * (factor + 1);
                } else {
                    scaleValue = 1 - SCALE_GAP * (factor - 1);
                }
            } else {
                scaleValue = 1 - SCALE_GAP * (factor + 1);
            }
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder != null) {
            if (viewHolder.animatorScaleY == null) {
                viewHolder.animatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1 - SCALE_GAP * factor, scaleValue);
                viewHolder.animatorScaleY.setInterpolator(new LinearInterpolator());
                viewHolder.animatorScaleY.setDuration(300);
            }
            viewHolder.animatorScaleY.setFloatValues(1 - SCALE_GAP * factor, scaleValue);
        }

        float translationValue;

        float translation;

        if (isToLeft) {
            if (factor > 0) {
                if (left) {
                    translationValue = TRANS_Y_GAP * (factor - 1) * -1;
                } else {
                    translationValue = TRANS_Y_GAP * (factor + 1);
                }
            } else {
                translationValue = TRANS_Y_GAP * (factor + 1);
            }
        } else {
            if (factor > 0) {
                if (left) {
                    translationValue = TRANS_Y_GAP * (factor + 1) * -1;
                } else {
                    translationValue = TRANS_Y_GAP * (factor - 1);
                }
            } else {
                translationValue = TRANS_Y_GAP * (factor + 1) * -1;
            }
        }

        //左/右位移
        if (left) {
            translation = TRANS_Y_GAP * factor * -1;
        } else {
            translation = TRANS_Y_GAP * factor;
        }
        if (viewHolder != null) {
            if (viewHolder.animatorTranslationX == null) {
                viewHolder.animatorTranslationX = ObjectAnimator.ofFloat(view, "translationX", translation, translationValue);
                viewHolder.animatorTranslationX.setInterpolator(new LinearInterpolator());
                viewHolder.animatorTranslationX.setDuration(300);
            }
            viewHolder.animatorTranslationX.setFloatValues(translation, translationValue);
            if (factor == 0) {
                mAnimatorAlpha.setTarget(view);
                mAnimatorAlpha.setPropertyName("alpha");
                mAnimatorAlpha.setFloatValues(1.0f, 0.6f);
                mAnimatorSet.playTogether(viewHolder.animatorScaleY, viewHolder.animatorTranslationX, mAnimatorAlpha);
            } else {
                mAnimatorSet.playTogether(viewHolder.animatorScaleY, viewHolder.animatorTranslationX);
            }
        }

    }


    private void addAndLayoutview(View view, int factor, boolean left) {
        if (view.getTag() == null) {
            ViewHolder holder = new ViewHolder();
            view.setTag(holder);
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (left) {
            viewHolder.factor = factor * -1;
        } else {
            viewHolder.factor = factor;
        }
        float alpha = 1 - (0.08f * factor);
        view.setAlpha(alpha);
        addView(view);
        measureChildWithMargins(view, 0, 0);
        int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
        int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);

        layoutDecorated(view, widthSpace / 2, heightSpace / 2,
                widthSpace / 2 + getDecoratedMeasuredWidth(view),
                heightSpace / 2 + getDecoratedMeasuredHeight(view));

        //Y方向的缩小
        view.setScaleY(1 - SCALE_GAP * factor);

        //左/右位移
        if (left) {
            view.setTranslationX(TRANS_Y_GAP * factor * -1);
        } else {
            view.setTranslationX(TRANS_Y_GAP * factor);
        }
    }

    public void setPosition(int position) {
        if (mPosition >= 0 && mPosition < getItemCount()) {
            if (position < mPosition) {
                isToLeft = true;
            } else {
                isToLeft = false;
            }
            mPosition = position;
        }
    }

    public int getCurrentPosition() {
        return mPosition;
    }

    public void toLeftWithAnim() {
        if (mPosition > 0) {
            mPosition--;
            isToLeft = true;
            notifyDataChangeWithAnims();
        }
    }

    public void toRightWithAnim() {
        if (mPosition < getItemCount() - 1) {
            mPosition++;
            isToLeft = false;
            notifyDataChangeWithAnims();
        }
    }

    public void toLeft() {
        if (mPosition > 0) {
            mPosition--;
            isToLeft = true;
//            requestLayout();
        }
    }

    public void toRight() {
        if (mPosition < getItemCount() - 1) {
            mPosition++;
            isToLeft = false;
//            requestLayout();
        }
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        requestLayout();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    public void setOnSelectedListener(OnSelectedListener listener) {
        mOnSelectedListener = listener;
    }

    public static class ViewHolder {
        public ObjectAnimator animatorScaleY;
        public ObjectAnimator animatorTranslationX;
        public int factor;
    }

    public interface OnSelectedListener {
        void onSelected(View rootView);
        void onUnSelected(View rootView);
    }

}