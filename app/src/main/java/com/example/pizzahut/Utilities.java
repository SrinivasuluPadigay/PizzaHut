package com.example.pizzahut;

import com.example.pizzahut.models.MenuItemDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utilities {

    private static Map<Integer, Integer> menuImagesMap;
    private static ArrayList<MenuItemDetails> menuItemDetailsList;

    static {
        initializeMenuList();
    }

    private static void initializeMenuList() {
        menuImagesMap = new HashMap<>();
        menuImagesMap.put(0, R.drawable.image_1);
        menuImagesMap.put(1, R.drawable.image_2);
        menuImagesMap.put(2, R.drawable.image_3);

        menuItemDetailsList = new ArrayList<>();
        MenuItemDetails menuItemDetails1 = new MenuItemDetails();
        menuItemDetails1.setItemDescription("description 0");
        menuItemDetails1.setItemTitle("title_0");
        menuItemDetails1.setItemImage(0);

        MenuItemDetails menuItemDetails2 = new MenuItemDetails();
        menuItemDetails2.setItemDescription("description 1");
        menuItemDetails2.setItemTitle("title_1");
        menuItemDetails2.setItemImage(1);

        MenuItemDetails menuItemDetails3 = new MenuItemDetails();
        menuItemDetails3.setItemDescription("description 2");
        menuItemDetails3.setItemTitle("title_3");
        menuItemDetails3.setItemImage(2);

        menuItemDetailsList.add(menuItemDetails1);
        menuItemDetailsList.add(menuItemDetails2);
        menuItemDetailsList.add(menuItemDetails3);

    }

    public static Integer getMenuImage(Integer index){
        return menuImagesMap.get(index);
    }

    public static ArrayList<MenuItemDetails> getMenuList(){
        return menuItemDetailsList;
    }
}
