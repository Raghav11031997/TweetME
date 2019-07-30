package com.khoros.tweetme.MODELS;


import java.util.Date;

public class PojoResponse {
    private String message;
    private String name;
    private String handle;
    private String profImageURL;
    private String createdAt;

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setHandle(String handle){
        this.handle = handle;
    }
    public String getHandle(){
        return handle;
    }

    public void setProfImageURL(String profImageURL){
        this.profImageURL = profImageURL;
    }
    public String getProfImageURL(){
        return profImageURL;
    }

    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }
    public String getCreatedAt(){
        return createdAt;
    }
}
