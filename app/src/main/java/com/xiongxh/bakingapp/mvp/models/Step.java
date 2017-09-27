package com.xiongxh.bakingapp.mvp.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Step implements Serializable {

    @Expose
    private int id;
    @Expose
    private String description;
    @Expose
    private String shortdescription;
    @Expose
    private String videoURL;
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

}
