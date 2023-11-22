package com.example.pizzahut;

import com.example.pizzahut.models.MenuItemDetails;

import java.util.ArrayList;
import java.util.Arrays;
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
        menuImagesMap.put(0, R.drawable.build_your_own);
        menuImagesMap.put(1, R.drawable.extravanganzza);
        menuImagesMap.put(2, R.drawable.meatzza);
        menuImagesMap.put(3, R.drawable.philly_cheese_steak);
        menuImagesMap.put(4, R.drawable.pacific_veggie);
        menuImagesMap.put(5, R.drawable.honolulu_hawaiian);
        menuImagesMap.put(6, R.drawable.deluxe);
        menuImagesMap.put(7, R.drawable.cali_chicken_bacon_ranch);
        menuImagesMap.put(8, R.drawable.buffalo_chicken);
        menuImagesMap.put(9, R.drawable.ultimate_pepperoni);
        menuImagesMap.put(10, R.drawable.memphis_bbq_chicken);
        menuImagesMap.put(11, R.drawable.wisconsin_6_cheese);
        menuImagesMap.put(12, R.drawable.spinach_feta);

        menuItemDetailsList = new ArrayList<>();

        MenuItemDetails menuItemDetails1 = new MenuItemDetails();
        menuItemDetails1.setItemDescription("Cheese, Robust Inspired Tomato Sauce");
        menuItemDetails1.setItemTitle("Build Your Own Pizza");
        menuItemDetails1.setItemImage(0);

        MenuItemDetails menuItemDetails2 = new MenuItemDetails();
        menuItemDetails2.setItemDescription("Pepperoni, Beef, Black Olives, Cheese, Italian Sausage, Green Peppers, Ham, Shredded Provolone Cheese");
        menuItemDetails2.setItemTitle("ExtravaganZZa");
        menuItemDetails2.setItemImage(1);

        MenuItemDetails menuItemDetails3 = new MenuItemDetails();
        menuItemDetails3.setItemDescription("Pepperoni, Beef, Cheese, Italian Sausage, Ham, Shredded Provolone Cheese, Robust Inspired Tomato Sauce");
        menuItemDetails3.setItemTitle("MeatZZa");
        menuItemDetails3.setItemImage(2);


        MenuItemDetails menuItemDetails4 = new MenuItemDetails();
        menuItemDetails4.setItemDescription("American Cheese, Green Peppers, Shredded Provolone Cheese, Philly Steak, Mushrooms, Onions");
        menuItemDetails4.setItemTitle("Philly Cheese Steak");
        menuItemDetails4.setItemImage(3);

        MenuItemDetails menuItemDetails5 = new MenuItemDetails();
        menuItemDetails5.setItemDescription("Diced Tomatoes, Black Olives, Cheese, Spinach, Shredded Provolone Cheese, Mushrooms");
        menuItemDetails5.setItemTitle("Pacific Veggie");
        menuItemDetails5.setItemImage(4);

        MenuItemDetails menuItemDetails6 = new MenuItemDetails();
        menuItemDetails6.setItemDescription("Cheese, Green Peppers, Ham, Shredded Provolone Cheese, Pineapple, Robust Inspired Tomato Sauce");
        menuItemDetails6.setItemTitle("Honolulu Hawaiian");
        menuItemDetails6.setItemImage(5);

        MenuItemDetails menuItemDetails7 = new MenuItemDetails();
        menuItemDetails7.setItemDescription("Pepperoni, Cheese, Italian Sausage, Green Peppers, Shredded Provolone Cheese, Mushrooms, Onions");
        menuItemDetails7.setItemTitle("Deluxe");
        menuItemDetails7.setItemImage(6);

        MenuItemDetails menuItemDetails8 = new MenuItemDetails();
        menuItemDetails8.setItemDescription("Diced Tomatoes, Premium Chicken, Cheese, Bacon, Shredded Provolone Cheese, Ranch");
        menuItemDetails8.setItemTitle("Cali Chicken Bacon Ranch");
        menuItemDetails8.setItemImage(7);

        MenuItemDetails menuItemDetails9 = new MenuItemDetails();
        menuItemDetails9.setItemDescription("Premium Chicken, American Cheese, Cheddar Cheese Blend, Hot Buffalo Sauce");
        menuItemDetails9.setItemTitle("Buffalo Chicken");
        menuItemDetails9.setItemImage(8);

        MenuItemDetails menuItemDetails10 = new MenuItemDetails();
        menuItemDetails10.setItemDescription("Shredded Parmesan Asiago, Pepperoni (Extra), Cheese, Shredded Provolone Cheese");
        menuItemDetails10.setItemTitle("Ultimate Pepperoni");
        menuItemDetails10.setItemImage(9);

        MenuItemDetails menuItemDetails11 = new MenuItemDetails();
        menuItemDetails11.setItemDescription("Premium Chicken, Cheese, Cheddar Cheese Blend, Shredded Provolone Cheese, Onions");
        menuItemDetails11.setItemTitle("Memphis BBQ Chicken");
        menuItemDetails11.setItemImage(10);

        MenuItemDetails menuItemDetails12 = new MenuItemDetails();
        menuItemDetails12.setItemDescription("Shredded Parmesan Asiago, Cheese, Cheddar Cheese Blend, Shredded Provolone Cheese");
        menuItemDetails12.setItemTitle("Wisconsin 6 Cheese");
        menuItemDetails12.setItemImage(11);

        MenuItemDetails menuItemDetails13 = new MenuItemDetails();
        menuItemDetails13.setItemDescription("Shredded Parmesan Asiago, Cheese, Spinach, Feta Cheese, Shredded Provolone Cheese");
        menuItemDetails13.setItemTitle("Spinach & Feta");
        menuItemDetails13.setItemImage(12);


        menuItemDetailsList.add(menuItemDetails1);
        menuItemDetailsList.add(menuItemDetails2);
        menuItemDetailsList.add(menuItemDetails3);
        menuItemDetailsList.add(menuItemDetails4);
        menuItemDetailsList.add(menuItemDetails5);
        menuItemDetailsList.add(menuItemDetails6);
        menuItemDetailsList.add(menuItemDetails7);
        menuItemDetailsList.add(menuItemDetails8);
        menuItemDetailsList.add(menuItemDetails9);
        menuItemDetailsList.add(menuItemDetails10);
        menuItemDetailsList.add(menuItemDetails11);
        menuItemDetailsList.add(menuItemDetails12);
        menuItemDetailsList.add(menuItemDetails13);

    }

    public static Integer getMenuImage(Integer index){
        return menuImagesMap.get(index);
    }

    public static ArrayList<MenuItemDetails> getMenuList(){
        return menuItemDetailsList;
    }

    public static ArrayList<String> getCrustList(){
        ArrayList<String> crustList = new ArrayList<>(Arrays.asList("Hand Tossed",
                "Handmade Pan",
                "Crunchy Thin Crust",
                "Brooklyn",
                "Gluten Free Crust"));
        return crustList;
    }

    public static ArrayList<String> getSizeList(){
        ArrayList<String> crustList = new ArrayList<>(Arrays.asList("Small(10')",
                "Medium(12')",
                "Large(14')",
                "X-Large(16')"));
        return crustList;
    }
}
