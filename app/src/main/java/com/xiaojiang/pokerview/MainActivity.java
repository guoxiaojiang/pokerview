package com.xiaojiang.pokerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiaojiang.pokerview.adapter.CommonAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PokerView mPokerView;
    CommonAdapter<PokerCardBean> mAdapter;
    protected List mDatas;
    PokerLayoutManager mPokerLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPokerView = (PokerView) findViewById(R.id.poker_view);
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
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        });
        mPokerView.scrollToPosition(3);
        mAdapter.notifyDataSetChanged();
    }
}
