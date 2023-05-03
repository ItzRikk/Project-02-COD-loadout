package com.example.codloadoutproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codloadoutproject.DB.DataBase;
import com.example.codloadoutproject.DB.UserDAO;


public class SignupActivity extends AppCompatActivity {
    TextView textSignIn;
    EditText usernameField;
    EditText passwordField;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getDatabase();

        usernameField =findViewById(R.id.username);
        passwordField = findViewById(R.id.password);

        textSignIn =findViewById(R.id.signinbtn);

        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = userDAO.getUsernameByUsername(usernameField.getText().toString());
                if(user != null){
                    Toast.makeText(SignupActivity.this,"USERNAME TAKEN",Toast.LENGTH_SHORT).show();
                }else{
                    user = new User(usernameField.getText().toString(), passwordField.getText().toString(), false);
                    userDAO.insert(user);
                    Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
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