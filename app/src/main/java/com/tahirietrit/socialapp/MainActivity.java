package com.tahirietrit.socialapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tahirietrit.socialapp.api.ApiService;
import com.tahirietrit.socialapp.api.Servicefactory;
import com.tahirietrit.socialapp.model.LoginResponse;
import com.tahirietrit.socialapp.model.User;
import com.tahirietrit.socialapp.model.feed.FeedResponse;
import com.tahirietrit.socialapp.prefs.AppPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFeed();
    }

    private void getFeed() {
        ApiService apiService = Servicefactory.retrofit.create(ApiService.class);
        Call<FeedResponse> call = apiService.getFeed("",
                AppPreferences.getUserid());
        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                System.out.println("feed count " + response.body().postet.size());
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Wrong combination",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
