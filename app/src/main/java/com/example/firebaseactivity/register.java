package com.example.firebaseactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class register extends AppCompatActivity {
   private FirebaseAuth auth;
   private EditText email;
   private EditText password;
   private Button registerAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        //email and password
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        registerAcc = findViewById(R.id.btnregister);


        registerAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the value of email and password

                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                //check if the credentials is empty
                if(TextUtils.isEmpty(emailString) || TextUtils.isEmpty(passwordString)) {
                    Toast.makeText(register.this, "Credentials is empty", Toast.LENGTH_SHORT).show();
                }
                else if(passwordString.length() <= 5) {
                    Toast.makeText(register.this, "Password is too short", Toast.LENGTH_SHORT).show();
                }
                else  {
                    registerAccount(emailString, passwordString);
                }
            }
        });


    }

    private void registerAccount(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(register.this, "Account Registered", Toast.LENGTH_SHORT).show();

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("email", email);
                    map.put("password", password);

                    //Real time database
                    FirebaseDatabase.getInstance().getReference().child("User Details").child("Accounts").updateChildren(map); //use in Hashmap

                }
                else {
                    Toast.makeText(register.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}