package com.example.codloadoutproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.room.Room;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.codloadoutproject.DB.DataBase;
import com.example.codloadoutproject.DB.MetasDAO;
import com.example.codloadoutproject.DB.NewsDAO;
import com.google.android.material.button.MaterialButton;

public class AdminNews extends AppCompatActivity {

    EditText titleField;
    EditText bodyField;
    private NewsDAO newsDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_news);
        getDatabase();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("notification","notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        titleField =findViewById(R.id.ECNnewstitle);
        bodyField =findViewById(R.id.ECNnewsbody);

        MaterialButton ret = (MaterialButton) findViewById(R.id.ECNreturnbtn);
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminNews.this, AdminEdit.class);
                startActivity(intent);
                finish();
            }
        });

        MaterialButton addNews = (MaterialButton) findViewById(R.id.ECNnewsbtn);

        addNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News news = new News(titleField.getText().toString(), bodyField.getText().toString());
                newsDAO.insert(news);
                titleField.getText().clear();
                bodyField.getText().clear();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(AdminNews.this, "notification");
                builder.setContentTitle("COD News");
                builder.setContentText("You Added News");
                builder.setSmallIcon(R.drawable.baseline_notifications_active_24);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AdminNews.this);
                managerCompat.notify(1,builder.build());
            }
        });

    }
    private void getDatabase() {
        newsDAO = Room.databaseBuilder(this, DataBase.class, DataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .NewsDAO();
    }
}