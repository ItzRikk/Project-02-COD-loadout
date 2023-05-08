package com.example.codloadoutproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.codloadoutproject.DB.DataBase;
import com.example.codloadoutproject.DB.NewsDAO;
import com.google.android.material.button.MaterialButton;

public class NewsDetails extends AppCompatActivity {

    TextView tBody, tTitle;
//    private NewsDAO newsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
//        getDatabase();

//        getSupportActionBar().hide();

        tBody=findViewById(R.id.NDbody);
        tTitle =findViewById(R.id.NDtitle);


        Button ret = (Button) findViewById(R.id.NTreturnbtn);
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsDetails.this, CodNewsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        String body = intent.getExtras().getString("body");

        tTitle.setText(title);
        tBody.setText(body);


    }
//     trve1`qwx
}