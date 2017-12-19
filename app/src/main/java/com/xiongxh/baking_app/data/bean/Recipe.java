package com.xiongxh.baking_app.data.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xiongxh.baking_app.data.local.RecipesDbContract;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = RecipesDbContract.RECIPES_TABLE_NAME)
@Parcel(Parcel.Serialization.BEAN)
public class Recipe {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("servings")
    @Expose
    private Integer servings;

    @SerializedName("ingredients")
    @Expose
    @Ignore
    private List<Ingredient> ingredients = null; // new ArrayList<>();

    @SerializedName("steps")
    @Expose
    @Ignore
    private List<Step> steps = null; // new ArrayList<>();

    public Recipe(){}

    public Recipe(Integer id, String name, String image, Integer servings, List<Ingredient> ingredients, List<Step> steps){
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getServings(){
        return servings;
    }

    public void setServings(Integer servings){
        this.servings = servings;
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

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", servings=" + servings +
                ", image='" + image + '\'' +
                '}';
    }
}