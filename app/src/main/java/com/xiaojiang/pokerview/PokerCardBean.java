package com.xiaojiang.pokerview;

import java.util.ArrayList;
import java.util.List;

public class PokerCardBean {
    private int postition;
    private String url;
    private String name;
    public int imgResource;
    public String detail;

    public PokerCardBean(int postition, String url, String name, int imgResource, String detail) {
        this.postition = postition;
        this.url = url;
        this.name = name;
        this.imgResource = imgResource;
        this.detail = detail;
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
        datas.add(new PokerCardBean(i++, null, "这是图片1", R.drawable.tu1, "习近平指出，中卢友好交往历史悠久。建交45年来，双方坚持真诚友好，坚持互利共赢。两国关系保持健康稳定发展。中方视卢森堡为欧盟内重要合作伙伴，愿同卢方一道努力，建设长期稳定、充满活力的伙伴关系，推动中卢关系不断迈上新台阶"));
        datas.add(new PokerCardBean(i++, null, "这是图片2", R.drawable.tu2,
                "双方要发扬优良传统，坚持平等相待、相互尊重，在涉及彼此核心利益和重" +
                        "大关切问题上相互理解、相互支持，把握两国关系正确发展方向。要提高两国务实合作质量，" +
                        "扩大优势，挖掘潜力，把金融等传统领域合作做大做强，加快培育航空运输、高新技术、绿色经" +
                        "济等新的合作增长点，实现更高水平的互利共赢。要深化双方在“一带一路”建设框架内金融和产" +
                        "能等合作，中方支持建设郑州－卢森堡“空中丝绸之路”。要加强文化、教育、体育等人文交流，" +
                        "提高人员往来便利化水平。中方期待卢方在中欧关系中继续发挥积极作用，推动欧盟为中欧合作" +
                        "深入发展提供更多有利条件。"));
        datas.add(new PokerCardBean(i++, null, "这是图片3", R.drawable.tu3, "贝泰尔表示，卢中建交45年来，两国经贸、金融、交通、文化等领域交流合作取得丰硕成果。我此次访华，旨在推进两国合作关系深入发展。卢中经济互补性强，卢方愿同中方在互尊互信基础上，互学互鉴，进一步拓展双方金融、投资、科技、旅游、人文等各领域交流合作、密切“一带一路”框架下合作。"));
        datas.add(new PokerCardBean(i++, null, "这是图片4", R.drawable.tu4, "马上就是618，各大电商平台都开始大促销，使出各种手段打折促销，吸引消费者。当然，有真促销，也有先把价格调上去再打折的假促销。像往年一样，今年也有网友质疑部分电商平台，借着打折的幌子欺骗消费者，实际上价格并没有便宜多少。"));
        datas.add(new PokerCardBean(i++, null, "这是图片5", R.drawable.tu5, "昨天，一位网友在某匿名社交平台发出了一张截图，吐槽京东上一款笔记本，不仅没有比平时便宜，价格反而上涨了不少。"));
        datas.add(new PokerCardBean(i++, null, "这是图片6", R.drawable.tu6, "根据该网友提供的聊天记录显示，这款京东自营的笔记本电脑在前一日零点前价格为2600元，但是零点之后价格就上涨到2999，随后开始以2799元的价格进行促销。"));
        datas.add(new PokerCardBean(i++, null, "这是图片7", R.drawable.tu7, "该网友咨询客服时，客服表示，价格是由京东制定的，她没有预知权限。"));
        datas.add(new PokerCardBean(i++, null, "这是图片8", R.drawable.tu8, "按照这位京东员工的说法，消费者买东西买贵了，只能怪自己，明明有便宜的时候，你为啥不去买呢？"));
//        datas.add(new PokerCardBean(i++, null, "警告信息9", R.drawable.tu8));
//        datas.add(new PokerCardBean(i++, null, "警告信息10", R.drawable.tu8));
//        datas.add(new PokerCardBean(i++, null, "警告信息11", R.drawable.tu8));
//        datas.add(new PokerCardBean(i++, null, "警告信息12", R.drawable.tu8));
//        datas.add(new PokerCardBean(i++, null, "警告信息13", R.drawable.tu8));
//        datas.add(new PokerCardBean(i++, null, "警告信息14", R.drawable.tu8));
        return datas;
    }
}
