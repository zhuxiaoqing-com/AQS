package com.example.demo4.testObj1;

public class ItemInfo {
    public int ItemId;

    public String ItemType;

    public ItemInfo() {
    }

    public ItemInfo(int itemId) {
        ItemId = itemId;
        ItemType = "itemType: " + itemId;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }
}
