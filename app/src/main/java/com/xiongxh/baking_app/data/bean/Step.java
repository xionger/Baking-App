package com.xiongxh.baking_app.data.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xiongxh.baking_app.data.local.RecipesDbContract;

import org.parceler.Parcel;


@Entity(tableName = RecipesDbContract.STEPS_TABLE_NAME,
        foreignKeys = @ForeignKey(entity = Recipe.class,
                parentColumns = "id",
                childColumns = "recipeId",
                onDelete = ForeignKey.CASCADE),
        indices = @Index("recipeId"))
@Parcel(Parcel.Serialization.BEAN)
public class Step {

    private int recipeId;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int _id;

    @SerializedName("id")
    @Expose
    private Integer idx;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("videoURL")
    @Expose
    private String videoURL;
    @SerializedName("thumbnailURL")
    @Expose
    private String thumbnailURL;

    public Step(){}

    public Step(Integer idx, String description, String shortdescription, String videoURL, String thumbnailURL){
        super();
        this.idx = idx;
        this.description = description;
        this.shortDescription = shortdescription;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public int getId(){
        return _id;
    }

    public void setId(int id) { this._id = id; }

    public int getRecipeId() { return recipeId; }

    public void setRecipeId(int recipeId) { this.recipeId = recipeId; }

    public Integer getIdx(){
        return idx;
    }

    public void setIdx (Integer idx){
        this.idx = idx;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getShortDescription(){
        return shortDescription;
    }

    public void setShortDescription(String shortdescription){
        this.shortDescription = shortdescription;
    }

    public String getVideoURL(){
        return videoURL;
    }

    public void setVideoURL(String videoURL){
        this.videoURL = videoURL;
    }

    public String getThumbnailURL(){
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL){
        this.thumbnailURL = thumbnailURL;
    }


    @Override
    public String toString() {
        return "Step{" +
                "id=" + idx +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", videoURL='" + videoURL + '\'' +
                ", thumbnailURL='" + thumbnailURL + '\'' +
                '}';
    }
}