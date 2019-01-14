package com.tahirietrit.socialapp.model.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("photo_url")
    @Expose
    public String photoUrl;
    @SerializedName("pershkrimi")
    @Expose
    public String pershkrimi;
    @SerializedName("created_date")
    @Expose
    public String createdDate;

}