package com.tahirietrit.socialapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tahirietrit.socialapp.R;
import com.tahirietrit.socialapp.model.feed.Post;

public class DetailActvity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Post post = getIntent().getParcelableExtra("extra_parcel");
        System.out.println("post "+ post.toString());
    }
}
