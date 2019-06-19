package com.example.socialnetwork;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button LoginButton;
    private EditText UserEmail, UserPassword;
    private TextView NeedNewAccount;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private String UserName, UserPasswords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        NeedNewAccount = (TextView) findViewById(R.id.register_account_link);
        UserEmail = (EditText) findViewById(R.id.login_email);
        UserPassword = (EditText) findViewById(R.id.login_password);
        LoginButton = (Button) findViewById(R.id.login_button);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        if (user != null)
            //finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        //if the user has already logged in then the second activity will be opened

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View Object) {
                validate(UserEmail.getText().toString().trim(), UserPassword.getText().toString().trim());
            }
        });


        NeedNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View Object) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }


    private void validate(String UserName, String UserPasswords) {
        UserName = UserEmail.getText().toString();
        UserPasswords = UserPassword.getText().toString();
        progressDialog.setMessage("It may take a few seconds, please wait patiently!");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(UserName, UserPasswords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    //checkEmailVerification();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}