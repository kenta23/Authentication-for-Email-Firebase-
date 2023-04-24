package com.example.firebaseactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Mainpage extends AppCompatActivity {

    private TextView helloUser;
    private Button logout;
    private EditText name;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        helloUser = findViewById(R.id.txtHelloUser);
        logout = findViewById(R.id.btnLogout);
        name = findViewById(R.id.editName);
        add = findViewById(R.id.btnAdd);

        Intent intent = getIntent();
        String user = intent.getStringExtra("user");


        helloUser.setText("Hello " + user.toString());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Mainpage.this, "Signed out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Mainpage.this, login.class));
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameString = name.getText().toString();
                if(nameString.isEmpty()) {
                    Toast.makeText(Mainpage.this, "Empty name", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Mainpage.this, "Name Added", Toast.LENGTH_SHORT).show();
                    FirebaseDatabase.getInstance().getReference().child("Sample Database").push().child("Name").setValue(nameString);
                }
            }
        });

    }
}