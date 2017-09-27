package com.xiongxh.bakingapp.mvp.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Ingredient implements Serializable {

    @Expose
    private double quantity;
    @Expose
    private String measure;
    @Expose
    private String ingredient;

    public Ingredient(){}

    public Ingredient(String ingredient, String measure, float quantity){
        this.ingredient = ingredient;
        this.measure = measure;
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

}
