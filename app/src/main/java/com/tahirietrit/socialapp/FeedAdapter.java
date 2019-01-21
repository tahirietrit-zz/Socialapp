package com.tahirietrit.socialapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tahirietrit.socialapp.callbacks.AdapterCallbacks;
import com.tahirietrit.socialapp.model.feed.FeedResponse;
import com.tahirietrit.socialapp.model.feed.Post;
import com.tahirietrit.socialapp.ui.DetailActvity;

import java.util.ArrayList;

public class FeedAdapter extends BaseAdapter {
    ArrayList<Post> feedPosts = new ArrayList<>();
    LayoutInflater inflater;
    Context ctx;
    AdapterCallbacks callbacks;

    public FeedAdapter(LayoutInflater inflater, AdapterCallbacks callbacks) {
        this.inflater = inflater;
        this.callbacks = callbacks;
    }

    @Override
    public int getCount() {
        return feedPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.post_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ctx = convertView.getContext();
        final Post post = feedPosts.get(position);
        holder.username.setText(post.username);
        Glide.with(ctx).load(post.photoUrl).into(holder.postPic);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.openPost(post);
            }
        });
        return convertView;
    }

    public void setFeedPosts(ArrayList<Post> feedPosts) {
        this.feedPosts = feedPosts;
        notifyDataSetChanged();
    }

    class ViewHolder {
        ImageView profilePic;
        ImageView postPic;
        TextView username;
        TextView uploadTime;

        public ViewHolder(View v) {
            profilePic = (ImageView) v.findViewById(R.id.profile_picture);
            postPic = (ImageView) v.findViewById(R.id.post_picture);
            username = (TextView) v.findViewById(R.id.username_textview);
            uploadTime = v.findViewById(R.id.upload_time);
        }

    }
}
