package com.example.messagepage.recycler;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("游戏小助手", "抖出好游戏","game","10:30"));
        result.add(new TestData("抖音小助手", "收下我的双下巴祝福","tiktok","10:29"));
        result.add(new TestData("系统消息", "账号登录提醒","notice","10:28"));
        result.add(new TestData("陌生人1", "就这？","unkownpeople","10:27"));
        result.add(new TestData("陌生人2", "你在教我做事？","unkownpeople","10:26"));
        result.add(new TestData("陌生人3", "不会吧不会吧？","unkownpeople","10:25"));
        result.add(new TestData("陌生人4", "呐呐呐!","unkownpeople","10:24"));
        result.add(new TestData("陌生人5", "有事？","unkownpeople","10:23"));

        return result;
    }

}
