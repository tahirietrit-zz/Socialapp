package com.tahirietrit.socialapp.model.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedResponse {

    @SerializedName("postet")
    @Expose
    public List<Post> postet = null;

}