package com.tahirietrit.socialapp.model.feed;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.userId);
        dest.writeString(this.username);
        dest.writeString(this.photoUrl);
        dest.writeString(this.pershkrimi);
        dest.writeString(this.createdDate);
    }

    public Post() {
    }

    protected Post(Parcel in) {
        this.id = in.readString();
        this.userId = in.readString();
        this.username = in.readString();
        this.photoUrl = in.readString();
        this.pershkrimi = in.readString();
        this.createdDate = in.readString();
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", pershkrimi='" + pershkrimi + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}