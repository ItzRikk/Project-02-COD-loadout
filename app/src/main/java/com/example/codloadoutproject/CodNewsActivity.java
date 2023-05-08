package com.example.codloadoutproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.codloadoutproject.DB.DataBase;
import com.example.codloadoutproject.DB.MetasDAO;
import com.example.codloadoutproject.DB.NewsDAO;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class CodNewsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<News> arrayList = new ArrayList<>();

    String news[] = {"COD CEO left the company", "New Map", "New Cars"};
    ListView listView;
    private NewsDAO newsDAO;

    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cod_news);
        getDatabase();

        recyclerView = findViewById(R.id.recyclerViewNews);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<News> newsTitleList = newsDAO.getTitleNews();
        List<News> listOfNews = new ArrayList<News>();
        for(News news: newsTitleList){
            listOfNews.add(news);
        }

        RecyclerViewNews recyclerViewNews = new RecyclerViewNews(this, listOfNews);
        recyclerView.setAdapter(recyclerViewNews);

        back = (Button) findViewById(R.id.CNreturnbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CodNewsActivity.this, LandingPage1Activity.class);
                startActivity(intent);
                finish();

            }
        });
//
//        listView = (ListView) findViewById(R.id.NewsViews);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_metas_list_view, R.id.textView, listOfNews);
//        listView.setAdapter(arrayAdapter);
//
//    }
    }
    private void getDatabase() {
        newsDAO = Room.databaseBuilder(this, DataBase.class, DataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .NewsDAO();
    }
}