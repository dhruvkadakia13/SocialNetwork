package com.example.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    private EditText Username, FullName, CountryName;
    private Button Save;
    private CircleImageView ProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Username = (EditText)findViewById(R.id.setup_username);
        FullName = (EditText)findViewById(R.id.setup_full_name);
        CountryName = (EditText)findViewById(R.id.setup_country_name);
        Save = (Button)findViewById(R.id.setup_button);
        ProfileImage  = (CircleImageView)findViewById(R.id.setup_profile_image);

    }
}
