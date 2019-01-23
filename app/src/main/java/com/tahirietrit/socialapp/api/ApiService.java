package com.tahirietrit.socialapp.api;

import com.tahirietrit.socialapp.model.LoginResponse;
import com.tahirietrit.socialapp.model.PostBody;
import com.tahirietrit.socialapp.model.UploadResponse;
import com.tahirietrit.socialapp.model.feed.FeedResponse;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/paintbook/index.php")
    Call<LoginResponse> loginUser(@Query("User") String user,
                                  @Query("Password") String password);
    @GET("/paintbook/index.php")
    Call<FeedResponse> getFeed(@Query("GetPostet") String postCount,
                               @Query("UserID") String userId);
    @Multipart
    @POST("/paintbook/")
    Call<UploadResponse> uploadImage(@Part() MultipartBody.Part img);

    @POST("/paintbook/index.php")
    Call<ResponseBody> createPost(@Query("new_post") String emptyString,
                                  @Body() PostBody postBody );
}
//wifi: auk-campus
//pass: ritkosova
