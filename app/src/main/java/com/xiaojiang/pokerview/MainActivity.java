package com.xiaojiang.pokerview;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.xiaojiang.pokerview.adapter.CommonAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PokerView mPokerView;
    CommonAdapter<PokerCardBean> mAdapter;
    protected List mDatas;
    PokerLayoutManager mPokerLayoutManager;
    private AnimatorSet mLeftInSet, mRightOutSet, mLeftOutSet, mRightInSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPokerView = (PokerView) findViewById(R.id.poker_view);
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.poker_item_card_anim_right_out);
        mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.poker_item_card_anim_left_in);
        mLeftOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.poker_item_card_anim_left_out);
        mRightInSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.poker_item_card_anim_right_in);
        mPokerView.setAdapter(mAdapter = new CommonAdapter<PokerCardBean>(this, mDatas = PokerCardBean.initDatas(), R.layout.item_poker_card) {

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public void convert(final ViewHolder viewHolder, PokerCardBean pokerCardBean) {
                viewHolder.setText(R.id.tvName, pokerCardBean.getName());
                viewHolder.setText(R.id.tvPrecent, pokerCardBean.getPostition() + " /" + mDatas.size());
                viewHolder.setImageResource(R.id.imgContent, pokerCardBean.imgResource);
                viewHolder.setText(R.id.txt_item_poker_card_detail, pokerCardBean.detail);
                viewHolder.setOnClickListener(R.id.ll_item_poker_card_front_cover, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard(viewHolder.getView(R.id.ll_item_poker_card_front_cover), viewHolder.getView(R.id.ll_item_poker_card_detail));
                    }
                });
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        });
        mPokerView.scrollToPosition(0);
        mAdapter.notifyDataSetChanged();
    }


    // 翻转卡片
    private void flipCard(View cardFront, View cardBack) {
        if (cardFront.getAlpha() > 0) {
            // 当前正面朝上
            mRightOutSet.setTarget(cardFront);
            mLeftInSet.setTarget(cardBack);
            mRightOutSet.start();
            mLeftInSet.start();
        } else {
            // 当前背面朝上
            mLeftOutSet.setTarget(cardBack);
            mRightInSet.setTarget(cardFront);
            mLeftOutSet.start();
            mRightInSet.start();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_LEFT_BRACKET) {
            mPokerView.toPrev();
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_RIGHT_BRACKET) {
            mPokerView.toNext();
        }
        return super.dispatchKeyEvent(event);
    }
}
