package com.example.codloadoutproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    TextView textSignUp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSignUp = findViewById(R.id.signupbtn);

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TextView username =  (TextView) findViewById(R.id.username);
        TextView password =  (TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin2") && password.getText().toString().equals("admin2")){
                    Toast.makeText(MainActivity.this,"LOGIN SUCCESSFULL",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LandingPage1Activity.class);
                    LandingPage1Activity.username = username.getText().toString();
                    LandingPage1Activity.isAdmin = true;
                    startActivity(intent);
                    finish();
                }else if(username.getText().toString().equals("testuser1") && password.getText().toString().equals("testuser1")){
                    Toast.makeText(MainActivity.this,"LOGIN SUCCESSFULL",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LandingPage1Activity.class);
                    LandingPage1Activity.username = username.getText().toString();
                    LandingPage1Activity.isAdmin = false;
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}