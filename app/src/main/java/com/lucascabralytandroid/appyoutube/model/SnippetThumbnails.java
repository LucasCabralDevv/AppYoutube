package com.lucascabralytandroid.appyoutube.model;

import com.google.gson.annotations.SerializedName;

public class SnippetThumbnails {

    @SerializedName("default")
    public Thumbnail defaultThumbnail;

    public Thumbnail medium;

    public Thumbnail high;

}
