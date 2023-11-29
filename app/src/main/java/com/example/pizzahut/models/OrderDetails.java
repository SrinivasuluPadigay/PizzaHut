package com.example.pizzahut.models;

import com.google.firebase.Timestamp;

import java.io.Serializable;

public class OrderDetails implements Serializable {
    public Timestamp created_at;
    public String created_by_uid, order_id;
    public double order_price;
    public ItemCustomization order_details;
    public MenuItemDetails menu_item_details;

    public OrderDetails() {
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by_uid() {
        return created_by_uid;
    }

    public void setCreated_by_uid(String created_by_uid) {
        this.created_by_uid = created_by_uid;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public ItemCustomization getOrder_details() {
        return order_details;
    }

    public void setOrder_details(ItemCustomization order_details) {
        this.order_details = order_details;
    }

    public MenuItemDetails getMenu_item_details() {
        return menu_item_details;
    }

    public void setMenu_item_details(MenuItemDetails menu_item_details) {
        this.menu_item_details = menu_item_details;
    }
}
