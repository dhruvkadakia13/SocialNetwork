package com.example.socialnetwork;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    private String Username;
    private EditText FullName;
    private EditText CountryName;
    private Button Save;
    private CircleImageView ProfileImage;
    private static int PICK_IMAGE = 123;
    Uri imagePath;

    public SetupActivity(String password, String city, String number, String email, String name) {
        this.Username = name;
        //this.CountryName = city;

    }


        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData()!= null){
                imagePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                    ProfileImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            super.onActivityResult(requestCode, resultCode, data);
        }


}
