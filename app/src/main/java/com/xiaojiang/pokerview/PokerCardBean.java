package com.xiaojiang.pokerview;

import java.util.ArrayList;
import java.util.List;

public class PokerCardBean {
    private int postition;
    private String url;
    private String name;
    public int imgResource;

    public PokerCardBean(int postition, String url, String name, int imgResource) {
        this.postition = postition;
        this.url = url;
        this.name = name;
        this.imgResource = imgResource;
    }

    public int getPostition() {
        return postition;
    }

    public PokerCardBean setPostition(int postition) {
        this.postition = postition;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PokerCardBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public PokerCardBean setName(String name) {
        this.name = name;
        return this;
    }

    public static List<PokerCardBean> initDatas() {
        List<PokerCardBean> datas = new ArrayList<>();
        int i = 1;
        datas.add(new PokerCardBean(i++, null, "这是图片1", R.drawable.tu1));
        datas.add(new PokerCardBean(i++, null, "这是图片2", R.drawable.tu2));
        datas.add(new PokerCardBean(i++, null, "这是图片3", R.drawable.tu3));
        datas.add(new PokerCardBean(i++, null, "这是图片4", R.drawable.tu4));
        datas.add(new PokerCardBean(i++, null, "这是图片5", R.drawable.tu5));
        datas.add(new PokerCardBean(i++, null, "这是图片6", R.drawable.tu6));
        datas.add(new PokerCardBean(i++, null, "这是图片7", R.drawable.tu7));
        datas.add(new PokerCardBean(i++, null, "这是图片8", R.drawable.tu8));
//        datas.add(new PokerCardBean(i++, null, "警告信息9", R.drawable.tu8));
//        datas.add(new PokerCardBean(i++, null, "警告信息10", R.drawable.tu8));
//        datas.add(new PokerCardBean(i++, null, "警告信息11", R.drawable.tu8));
//        datas.add(new PokerCardBean(i++, null, "警告信息12", R.drawable.tu8));
//        datas.add(new PokerCardBean(i++, null, "警告信息13", R.drawable.tu8));
//        datas.add(new PokerCardBean(i++, null, "警告信息14", R.drawable.tu8));
        return datas;
    }
}
