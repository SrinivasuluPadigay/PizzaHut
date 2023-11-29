package com.example.pizzahut.models;

import java.io.Serializable;

public class MenuItemDetails implements Serializable {

    public String itemDescription;
    public String itemTitle;
    public Integer itemImage;

    public MenuItemDetails() {
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public Integer getItemImage() {
        return itemImage;
    }

    public void setItemImage(Integer itemImage) {
        this.itemImage = itemImage;
    }

    @Override
    public String toString() {
        return "MenuItemDetails{" +
                "itemDescription='" + itemDescription + '\'' +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemImage=" + itemImage +
                '}';
    }
}
