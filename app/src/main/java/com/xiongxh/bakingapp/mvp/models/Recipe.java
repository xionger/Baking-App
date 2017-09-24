package com.xiongxh.bakingapp.mvp.models;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {

    private int id;
    private String name;
    private String image;
    private int servings;
    private List<Ingredient> ingredients;
    private List<Step> steps;

    public Recipe(){}

    public Recipe(int id, String name, String image, int servings, List<Ingredient> ingredients, List<Step> steps){
        this.id = id;
        this.name = name;
        this.image = image;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getImage() {
        return image;
    }

    public int getServings(){
        return servings;
    }

    public void setServings(int servings){
        this.servings = servings;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public List<Ingredient> getIngredients() {
        return ingredients;
    }


    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps(){
        return steps;
    }

    public void setSteps(List<Step> steps){
        this.steps = steps;
    }

}
