package com.xiongxh.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import static android.R.attr.description;
import static android.R.attr.thickness;

@Generated("org.jsonschema2pojo")
public class Recipe implements Parcelable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("servings")
    @Expose
    private int servings;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients;
    @SerializedName("steps")
    @Expose
    private List<Step> steps;

    public Recipe(){

    }

    public Recipe(int id, String name, String image, int servings, List<Ingredient> ingredients, List<Step> steps){
        this.id = id;
        this.name = name;
        this.image = image;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    protected Recipe(Parcel parcel){
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.image = parcel.readString();
        this.servings = parcel.readInt();

        this.ingredients = new ArrayList<>();
        parcel.readList(this.ingredients, Ingredient.class.getClassLoader());

        this.steps = new ArrayList<>();
        parcel.readList(this.steps, Step.class.getClassLoader());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.image);
        parcel.writeInt(this.servings);
        parcel.writeList(this.ingredients);
        parcel.writeList(this.steps);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>(){
        @Override
        public Recipe createFromParcel(Parcel parcel){
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int size){
            return new Recipe[size];
        }
    };
}
