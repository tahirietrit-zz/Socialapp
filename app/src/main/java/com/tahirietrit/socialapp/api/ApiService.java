package com.tahirietrit.socialapp.api;

import com.tahirietrit.socialapp.model.LoginResponse;
import com.tahirietrit.socialapp.model.feed.FeedResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/paintbook/index.php")
    Call<LoginResponse> loginUser(@Query("User") String user,
                                  @Query("Password") String password);
    @GET("/paintbook/index.php")
    Call<FeedResponse> getFeed(@Query("GetPostet") String postCount,
                               @Query("UserID") String userId);
}
//wifi: auk-campus
//pass: ritkosova
