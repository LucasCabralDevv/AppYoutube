package com.lucascabralytandroid.appyoutube.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Resultado {

    public String regionCode;

    public PageInfo pageInfo;

    @SerializedName("items")
    public List<Video> videos;


}
