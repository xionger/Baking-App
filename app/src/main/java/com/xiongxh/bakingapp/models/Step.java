package com.xiongxh.bakingapp.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Step implements Parcelable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("shortdescription")
    @Expose
    private String shortdescription;
    @SerializedName("videoURL")
    @Expose
    private String videoURL;
    @SerializedName("thumbnailURL")
    @Expose
    private String thumbnailURL;

    public Step(){}

    public Step(int id, String description, String shortdescription, String videoURL, String thumbnailURL){
        this.id = id;
        this.description = description;
        this.shortdescription = shortdescription;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    protected Step(Parcel parcel){
        this.id = parcel.readInt();
        this.description = parcel.readString();
        this.shortdescription = parcel.readString();
        this.videoURL = parcel.readString();
        this.thumbnailURL = parcel.readString();
    }
    public int getId(){
        return id;
    }

    public void setId (int id){
        this.id = id;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getShortdescription(){
        return shortdescription;
    }

    public void setShortdescription(String shortdescription){
        this.shortdescription = shortdescription;
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.description);
        parcel.writeString(this.shortdescription);
        parcel.writeString(this.videoURL);
        parcel.writeString(this.thumbnailURL);
    }


    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>(){
      @Override
      public Step createFromParcel(Parcel parcel){
          return new Step(parcel);
      }

      @Override
      public Step[] newArray(int size){
          return new Step[size];
      }
    };
}
