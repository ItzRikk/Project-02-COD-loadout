package com.example.codloadoutproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.room.Room;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.codloadoutproject.DB.DataBase;
import com.example.codloadoutproject.DB.MetasDAO;
import com.example.codloadoutproject.DB.UserDAO;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class RemoveUser extends AppCompatActivity {
    private UserDAO userDAO;
    User selectedUser;
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_user);
        getDatabase();

        MaterialButton returnbtn = (MaterialButton) findViewById(R.id.RUreturnbtn);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RemoveUser.this, AdminEdit.class);
                startActivity(intent);
                finish();
            }
        });

        autoCompleteTextView = findViewById(R.id.RUdorpDown);

        List<User> userList = userDAO.getUsers();
        List<String> listOfUsers = new ArrayList<String>();
        for (User user : userList) {
            listOfUsers.add(user.getUsername());
        }

        adapterItems = new ArrayAdapter<String>(this, R.layout.remove_user_list, listOfUsers);

        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String user = autoCompleteTextView.getAdapter().getItem(position).toString();
                selectedUser = userDAO.getUsernameByUsername(user);
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("notification","notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        MaterialButton removeUser = (MaterialButton) findViewById(R.id.RUremoveUserbtn);

        removeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDAO.delete(selectedUser);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(RemoveUser.this, "notification");
                builder.setContentTitle("COD Users");
                builder.setContentText("You Have Remove A User");
                builder.setSmallIcon(R.drawable.baseline_notifications_active_24);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(RemoveUser.this);
                managerCompat.notify(1,builder.build());
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