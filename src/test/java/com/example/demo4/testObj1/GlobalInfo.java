package com.example.demo4.testObj1;

import java.util.Random;

public class GlobalInfo {
    //类型
    public String Type;

    // item
    public ItemInfo[] Items;

    public static GlobalInfo create() {
        GlobalInfo globalInfo = new GlobalInfo();
        globalInfo.Type = Math.random()+"";
        globalInfo.Items = new ItemInfo[2];
        globalInfo.Items[0] = new ItemInfo((int) (Math.random()*100));
        globalInfo.Items[1] = new ItemInfo((int) (Math.random()*100));
        return globalInfo;
    }


    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public ItemInfo[] getItems() {
        return Items;
    }

    public void setItems(ItemInfo[] items) {
        Items = items;
    }
}
