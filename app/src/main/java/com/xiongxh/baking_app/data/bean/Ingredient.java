package com.xiongxh.baking_app.data.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xiongxh.baking_app.data.local.RecipesDbContract;

import org.parceler.Parcel;

@Entity(tableName = RecipesDbContract.INGREDIENTS_TABLE_NAME,
        foreignKeys = @ForeignKey(entity = Recipe.class,
                parentColumns = "id",
                childColumns = "recipeId",
                onDelete = ForeignKey.CASCADE))

@Parcel(Parcel.Serialization.BEAN)
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int recipeId;

    @SerializedName("quantity")
    @Expose
    private Double quantity;

    @SerializedName("measure")
    @Expose
    private String measure;

    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    public int getId() { return id; }

    public void setId(int id){ this.id = id; }

    public int getRecipeId() { return recipeId; }

    public void setRecipeId(int recipeId) { this.recipeId = recipeId; }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
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