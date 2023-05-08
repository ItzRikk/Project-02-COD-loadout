package com.example.codloadoutproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class AdminEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);
        MaterialButton ret = (MaterialButton) findViewById(R.id.ADreturnbtn);
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminEdit.this, LandingPage1Activity.class);
                startActivity(intent);
                finish();
            }
        });
        MaterialButton admetas = (MaterialButton) findViewById(R.id.ADmetasbtn);
        admetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminEdit.this, AdminMetas.class);
                startActivity(intent);
                finish();
            }
        });
        MaterialButton adnews = (MaterialButton) findViewById(R.id.ADnewsbtn);
        adnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminEdit.this, AdminNews.class);
                startActivity(intent);
                finish();
            }
        });
        MaterialButton RMusers = (MaterialButton) findViewById(R.id.ADremovebtn);
        RMusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminEdit.this, RemoveUser.class);
                startActivity(intent);
                finish();
            }
        });
    }
}