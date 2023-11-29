package com.example.pizzahut.models;

import java.io.Serializable;

public class SpecialInstructions implements Serializable {
    public String cut_type, bake_type, seasoning;

    public SpecialInstructions() {
    }

    public String getCut_type() {
        return cut_type;
    }

    public void setCut_type(String cut_type) {
        this.cut_type = cut_type;
    }

    public String getBake_type() {
        return bake_type;
    }

    public void setBake_type(String bake_type) {
        this.bake_type = bake_type;
    }

    public String getSeasoning() {
        return seasoning;
    }

    public void setSeasoning(String seasoning) {
        this.seasoning = seasoning;
    }

    @Override
    public String toString() {
        return
                "cut_type='" + cut_type + '\'' +
                ", bake_type='" + bake_type + '\'' +
                ", seasoning='" + seasoning + '\'';
    }
}
