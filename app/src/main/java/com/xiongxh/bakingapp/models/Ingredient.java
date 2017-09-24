package com.xiongxh.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Ingredient implements Parcelable {
    @SerializedName("quantity")
    @Expose
    private double quantity;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    public Ingredient(){}

    public Ingredient(String ingredient, String measure, float quantity){
        this.ingredient = ingredient;
        this.measure = measure;
        this.quantity = quantity;
    }

    protected Ingredient(Parcel parcel) {
        this.ingredient = parcel.readString();
        this.quantity = parcel.readDouble();
        this.measure = parcel.readString();
    }

    /**
     *
     * @return
     * The quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     * The quantity
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     * The measure
     */
    public String getMeasure() {
        return measure;
    }

    /**
     *
     * @param measure
     * The measure
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     *
     * @return
     * The ingredient
     */
    public String getIngredient() {
        return ingredient;
    }

    /**
     *
     * @param ingredient
     * The ingredient
     */
    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString(){
        return "Ingredient{" +
                "quantity=" + quantity +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.ingredient);
        parcel.writeDouble(this.quantity);
        parcel.writeString(this.measure);
    }



    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>(){
        @Override
        public Ingredient createFromParcel(Parcel parcel){
            return new Ingredient(parcel);
        }

        @Override
        public Ingredient[] newArray(int size){
            return new Ingredient[size];
        }
    };

}
