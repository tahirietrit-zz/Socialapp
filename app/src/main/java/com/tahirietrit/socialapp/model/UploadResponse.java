package com.tahirietrit.socialapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadResponse {

    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("name")
    @Expose
    public String name;

}