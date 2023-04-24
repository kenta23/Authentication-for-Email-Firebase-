package com.example.firebaseactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private Button login;
    private EditText email;
    private EditText password;

    private FirebaseAuth auth;



    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        login = findViewById(R.id.buttonLogin);
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                if(TextUtils.isEmpty(emailString) || TextUtils.isEmpty(passwordString)) {
                    Toast.makeText(login.this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginStart(emailString, passwordString);

                }
            }
        });


    }
    private void loginStart(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(login.this, "Log in Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this, Mainpage.class);
                intent.putExtra("user", email.toString());
                startActivity(intent);
                finish();
            }
        });
    }
}