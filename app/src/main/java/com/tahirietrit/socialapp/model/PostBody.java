package com.tahirietrit.socialapp.model;

import com.google.gson.annotations.SerializedName;

public class PostBody {
    @SerializedName("user_id")
    String userID;
    @SerializedName("image_path")
    String image_path;
    @SerializedName("pershkrimi")
    String desc;

    public PostBody(String userID, String imagePath, String desc) {
        this.userID = userID;
        this.image_path = imagePath;
        this.desc = desc;
    }
}
