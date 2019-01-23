package com.tahirietrit.socialapp.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tahirietrit.socialapp.R;
import com.tahirietrit.socialapp.api.ApiService;
import com.tahirietrit.socialapp.api.Servicefactory;
import com.tahirietrit.socialapp.model.PostBody;
import com.tahirietrit.socialapp.model.UploadResponse;
import com.tahirietrit.socialapp.prefs.AppPreferences;

import java.io.File;
import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateNewPostActivity extends Activity {
    ImageView imageView;
    EditText postDesc;
    private int PICK_IMAGE = 1500;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_post_activity);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        imageView = findViewById(R.id.post_imageView);
        postDesc = findViewById(R.id.post_desc);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            System.out.println("picture path " + picturePath);
            Glide.with(getApplicationContext()).load(selectedImage).into(imageView);
            File imgFile = new File(picturePath);
            uploadImage(imgFile);

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                   finish();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    private void uploadImage(File file) {
        ApiService apiService = Servicefactory.retrofit.create(ApiService.class);
        Call<UploadResponse> call = apiService.uploadImage(prepareFilePart("image", file));
        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                System.out.println("img path " + response.body().name);
                createPost(response.body().name);
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Username or password are wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createPost(String imagePath) {
        ApiService apiService = Servicefactory.retrofit.create(ApiService.class);
        PostBody postBody = new PostBody(AppPreferences.getUserid(), imagePath,
                postDesc.getText().toString());
        Call<ResponseBody> call = apiService.createPost("", postBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Username or password are wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    MultipartBody.Part prepareFilePart(String partName, File img) {
        RequestBody requestFile = RequestBody.create(okhttp3.MultipartBody.FORM, img);

        return MultipartBody.Part.createFormData(partName, img.getName(), requestFile);
    }
}
