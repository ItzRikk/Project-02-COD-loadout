package com.example.codloadoutproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.codloadoutproject.DB.DataBase;
import com.example.codloadoutproject.DB.UserDAO;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    TextView textSignUp;
    private UserDAO userDAO;

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
        getDatabase();

        TextView username =  (TextView) findViewById(R.id.username);
        TextView password =  (TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User pass = userDAO.getPasswordbyPassword(password.getText().toString());
                User user = userDAO.getUsernameByUsername(username.getText().toString());
                if(username.getText().toString().equals("admin2") && password.getText().toString().equals("admin2")) {
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LandingPage1Activity.class);
                    LandingPage1Activity.username = username.getText().toString();
                    LandingPage1Activity.isAdmin = true;
                    startActivity(intent);
                    finish();
                }else if(user != null && pass != null){
                    Toast.makeText(MainActivity.this,"LOGIN SUCCESSFULL",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LandingPage1Activity.class);
                    LandingPage1Activity.username = username.getText().toString();
                    LandingPage1Activity.isAdmin = false;
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(MainActivity.this,"LOGIN FAILED",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    private void getDatabase() {
        userDAO = Room.databaseBuilder(this, DataBase.class, DataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .UserDAO();
    }
}