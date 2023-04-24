package com.example.firebaseactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button registerbtn;
    private Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerbtn = findViewById(R.id.btnRegister);
        loginBtn = findViewById(R.id.btnLogin);


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, register.class));
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, login.class));
                finish();
            }
        });

        HashMap<String, Object> map = new HashMap<>();
        map.put("Name", "Rm Ramos");
        map.put("email", "drey@gmail.com");

        FirebaseDatabase.getInstance().getReference().child("Sample Database").child("Details").updateChildren(map);
    }

}