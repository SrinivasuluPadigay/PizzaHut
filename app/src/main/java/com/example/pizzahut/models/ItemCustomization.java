package com.example.pizzahut.models;

import android.content.ClipData;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemCustomization implements Serializable {
    public String serving_size;
    public String serving_crust;
    public Integer serving_quantity;
    public String cheese_quantity;
    public String serving_sauce;
    public Toppings toppings;
    public Integer garlic_dip_quantity;
    public Integer ranch_dip_quantity;
    public Integer marinara_sauce_dip_quantity;
    public SpecialInstructions specialInstructions;

    public ItemCustomization() {
    }

    public SpecialInstructions getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(SpecialInstructions specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getServing_size() {
        return serving_size;
    }

    public void setServing_size(String serving_size) {
        this.serving_size = serving_size;
    }

    public String getServing_crust() {
        return serving_crust;
    }

    public void setServing_crust(String serving_crust) {
        this.serving_crust = serving_crust;
    }

    public Integer getServing_quantity() {
        return serving_quantity;
    }

    public void setServing_quantity(Integer serving_quantity) {
        this.serving_quantity = serving_quantity;
    }

    public String getCheese_quantity() {
        return cheese_quantity;
    }

    public void setCheese_quantity(String cheese_quantity) {
        this.cheese_quantity = cheese_quantity;
    }

    public String getServing_sauce() {
        return serving_sauce;
    }

    public void setServing_sauce(String serving_sauce) {
        this.serving_sauce = serving_sauce;
    }

    public Toppings getToppings() {
        return toppings;
    }

    public void setToppings(Toppings toppings) {
        this.toppings = toppings;
    }

    public Integer getGarlic_dip_quantity() {
        return garlic_dip_quantity;
    }

    public void setGarlic_dip_quantity(Integer garlic_dip_quantity) {
        this.garlic_dip_quantity = garlic_dip_quantity;
    }

    public Integer getRanch_dip_quantity() {
        return ranch_dip_quantity;
    }

    public void setRanch_dip_quantity(Integer ranch_dip_quantity) {
        this.ranch_dip_quantity = ranch_dip_quantity;
    }

    public Integer getMarinara_sauce_dip_quantity() {
        return marinara_sauce_dip_quantity;
    }

    public void setMarinara_sauce_dip_quantity(Integer marinara_sauce_dip_quantity) {
        this.marinara_sauce_dip_quantity = marinara_sauce_dip_quantity;
    }

    @Override
    public String toString() {
        return
                "serving_size='" + serving_size + '\'' +
                ", serving_crust='" + serving_crust + '\'' +
                ", serving_quantity=" + serving_quantity +
                ", cheese_quantity='" + cheese_quantity + '\'' +
                ", serving_sauce='" + serving_sauce + '\'' +
//                ", toppings=" + toppings +
                ", garlic_dip_quantity=" + garlic_dip_quantity +
                ", ranch_dip_quantity=" + ranch_dip_quantity +
                ", marinara_sauce_dip_quantity=" + marinara_sauce_dip_quantity +
                ", specialInstructions=" + specialInstructions;
    }


    public double calculatePizzaPrice(ItemCustomization pizzaCustomization){
        double servingCrustPrice = 0.0;
        double servingSizePrice = getQuantityPrice(pizzaCustomization.serving_size);
        double servingQuantity = pizzaCustomization.getServing_quantity();
        double cheeseQuantity = 0.0;
        double servingSauce = 0.0;
        double garlicDipPrice = 0.55*pizzaCustomization.getGarlic_dip_quantity();
        double ranchDipPrice = 0.55*pizzaCustomization.getRanch_dip_quantity();
        double marinaraSauceDipPrice = 0.55*pizzaCustomization.getMarinara_sauce_dip_quantity();
        double toppingsPrice = calculateToppingsPrice(pizzaCustomization.getToppings());
        double specialInstructionPrice = calculateSpecialInstructionPrice(pizzaCustomization.getSpecialInstructions());

        double pizzaPrice = 0.0;
        pizzaPrice = servingQuantity * (servingCrustPrice
                + servingSizePrice
                + cheeseQuantity
                + servingSauce
                + ranchDipPrice
                + marinaraSauceDipPrice
                + garlicDipPrice
                + toppingsPrice
                + specialInstructionPrice);

        return pizzaPrice;
    }

    private double calculateToppingsPrice(Toppings pizzaToppings) {

        double toppingsPrice = 0.0;
        if (pizzaToppings != null){
            toppingsPrice = getQuantityPrice(pizzaToppings.getHam())
                    + getQuantityPrice(pizzaToppings.getBeef())
                    + getQuantityPrice(pizzaToppings.getSalami())
                    + getQuantityPrice(pizzaToppings.getPepperoni())
                    + getQuantityPrice(pizzaToppings.getItalian_sausage())
                    + getQuantityPrice(pizzaToppings.getPremium_chicken())
                    + getQuantityPrice(pizzaToppings.getBacon())
                    + getQuantityPrice(pizzaToppings.getHot_buffalo_sauce())
                    + getQuantityPrice(pizzaToppings.getJalapeno_peppers())
                    + getQuantityPrice(pizzaToppings.getOnions())
                    + getQuantityPrice(pizzaToppings.getBanana_peppers())
                    + getQuantityPrice(pizzaToppings.getDiced_tomatoes())
                    + getQuantityPrice(pizzaToppings.getBlack_olives())
                    + getQuantityPrice(pizzaToppings.getMushrooms())
                    + getQuantityPrice(pizzaToppings.getPineapple())
                    + getQuantityPrice(pizzaToppings.getShredded_provolone_cheese())
                    + getQuantityPrice(pizzaToppings.getCheddar_cheese_blend())
                    + getQuantityPrice(pizzaToppings.getGreen_peppers())
                    + getQuantityPrice(pizzaToppings.getSpinach())
                    + getQuantityPrice(pizzaToppings.getFeta_cheese())
                    + getQuantityPrice(pizzaToppings.getShredded_parmesan_asiago())
                    + getQuantityPrice(pizzaToppings.getPhilly_steak());
        }

        return toppingsPrice;
    }

    private double getQuantityPrice(String quantity){
        switch (quantity){
            case ("None") :
            case ("No Garlic-Seasoned Crust") :
                return 0.0;
            case ("Light") : return 0.75;
            case ("Normal") : return 1.0;
            case ("Extra") : return 1.25;
            case ("Garlic-Seasoned Crust") : return 0.89;
            case ("Small(10')") : return 8.99;
            case ("Medium(12')") : return 13.99;
            case ("Large(14')") : return 16.99;
            case ("X-Large(16')") : return 19.99;
        }
        return 0.0;
    }

    private double calculateSpecialInstructionPrice(SpecialInstructions pizzaSpecialInstructions) {
        double cutType = 0.75;
        double bakeType = 0.75;
        double seasoning = 0.75;

        double specialInstructionPrice = 0.0;
        if (pizzaSpecialInstructions != null) {
            specialInstructionPrice += (pizzaSpecialInstructions.getCut_type() != null) ? cutType : 0.0;
            specialInstructionPrice += (pizzaSpecialInstructions.getBake_type() != null) ? bakeType : 0.0;
            specialInstructionPrice += (pizzaSpecialInstructions.getSeasoning() != null) ? seasoning : 0.0;
        }

        return specialInstructionPrice;
    }
}
