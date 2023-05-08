package com.example.codloadoutproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.codloadoutproject.DB.DataBase;
import com.example.codloadoutproject.DB.MetasDAO;


import java.util.ArrayList;
import java.util.List;

public class MetasActivity extends AppCompatActivity {

    String weapons [] = {"MP5", "P90","RPG"};
    ListView listView;
    private MetasDAO metasDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metas);

        getDatabase();

        Button back = (Button) findViewById(R.id.Mreturnbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MetasActivity.this, LandingPage1Activity.class);
                startActivity(intent);
                finish();
            }
        });

        List<Metas> metaTitleList = metasDAO.getTitleMeta();
        List<String> listOfWeapons = new ArrayList<String>();
        for(Metas weapon: metaTitleList){
            listOfWeapons.add(weapon.getTitleMeta() + ":\n " + weapon.getPrimaryWeapon() + " - " +weapon.getSecondaryWeapon());
        }

        listView = (ListView) findViewById(R.id.MetasViews);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_metas_list_view, R.id.textView, listOfWeapons);
        listView.setAdapter(arrayAdapter);



    }
    private void getDatabase() {
        metasDAO = Room.databaseBuilder(this, DataBase.class, DataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .MetasDAO();
    }
}