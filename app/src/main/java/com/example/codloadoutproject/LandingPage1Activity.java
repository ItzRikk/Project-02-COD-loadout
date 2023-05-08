package com.example.codloadoutproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.codloadoutproject.DB.DataBase;
import com.example.codloadoutproject.DB.UserDAO;
import com.google.android.material.button.MaterialButton;

public class LandingPage1Activity extends AppCompatActivity {
    TextView textLogout;

    public static String username;
    public static boolean isAdmin;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page1);

        TextView usernameText =  (TextView) findViewById(R.id.username);
        usernameText.setText(username);

        MaterialButton metas = (MaterialButton) findViewById(R.id.metasbtn);
        metas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPage1Activity.this, MetasActivity.class);
                startActivity(intent);
                finish();
            }
        });
        MaterialButton news = (MaterialButton) findViewById(R.id.newsbtn);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPage1Activity.this, CodNewsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        MaterialButton admin = (MaterialButton) findViewById(R.id.adminbtn);
        if (isAdmin) {
            admin.setVisibility(View.VISIBLE);

            admin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LandingPage1Activity.this, AdminEdit.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            admin.setVisibility(View.INVISIBLE);
        }
        textLogout =findViewById(R.id.logoutbtn);
        getDatabase();

        textLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LandingPage1Activity.this,"LOGOUT SUCCESSFULL",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LandingPage1Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
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