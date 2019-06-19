package com.example.socialnetwork;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterActivity extends AppCompatActivity {

    private EditText Email, Password, Fullname,City, Number ;
    private Button CreateAccount;
    private FirebaseAuth firebaseAuth;
    String email, name, city, password, number;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();


        CreateAccount.setOnClickListener(new View.OnClickListener() {

            private Boolean validate() {
                Boolean result = false;
                name = Fullname.getText().toString();
                password = Password.getText().toString();
                email = Email.getText().toString();
                city = City.getText().toString();
                number = Number.getText().toString();

                if (name.isEmpty() || password.isEmpty() || email.isEmpty() || number.isEmpty() || city.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                } else {
                    result = true;
                }

                return result;
            }

            @Override
            public void onClick(View v) {
                if (validate()) {
                    final String user_email = Email.getText().toString().trim();
                    final String user_password = Password.getText().toString().trim();
                    String user_fullname = Fullname.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signInWithEmailAndPassword(user_email, user_password);
                                Toast.makeText(RegisterActivity.this, "Successfully Registered, Upload Complete!",Toast.LENGTH_SHORT).show();
                                //firebaseAuth.signOut();
                                finish();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }

            private void sendUserData(){
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
                SetupActivity setupActivity = new SetupActivity(password, city, number, email, name);
                myRef.setValue(setupActivity);
            }
        });


        CreateAccount = (Button)findViewById(R.id.register_create_account);

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUsertoSetupActivity();
            }
        });
    }

    private void setupUIViews() {
        Email = (EditText)findViewById(R.id.register_email);
        Password  =(EditText)findViewById(R.id.register_password);
        Fullname = (EditText)findViewById(R.id.register_fullname);
        City  =(EditText)findViewById(R.id.register_city);
        Number = (EditText)findViewById(R.id.register_number);

    }

    private void SendUsertoSetupActivity(){
        Intent setupIntent  = new Intent(RegisterActivity.this, SetupActivity.class);
        startActivity(setupIntent);
    }
}
