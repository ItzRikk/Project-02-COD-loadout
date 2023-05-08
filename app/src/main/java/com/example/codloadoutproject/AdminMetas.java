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
import android.widget.EditText;

import com.example.codloadoutproject.DB.DataBase;
import com.example.codloadoutproject.DB.MetasDAO;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class AdminMetas extends AppCompatActivity {

    EditText titleMetaField;
    EditText primaryWeaponField;
    EditText secondaryWeaponField;
    private MetasDAO metasDAO;
    Metas selectedWeapon;

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_metas);
        getDatabase();

        titleMetaField = findViewById(R.id.EMmetaTitle);

        primaryWeaponField = findViewById(R.id.EMprimary);

        secondaryWeaponField = findViewById(R.id.EMsecondary);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("notification","notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        MaterialButton returnbtn = (MaterialButton) findViewById(R.id.EMreturnbtn);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMetas.this, AdminEdit.class);
                startActivity(intent);
                finish();
            }
        });


        MaterialButton addMetas = (MaterialButton) findViewById(R.id.EMaddMetabtn);

        addMetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Metas metas = new Metas(titleMetaField.getText().toString(), primaryWeaponField.getText().toString(), secondaryWeaponField.getText().toString());
                metasDAO.insert(metas);
                titleMetaField.getText().clear();
                primaryWeaponField.getText().clear();
                secondaryWeaponField.getText().clear();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(AdminMetas.this, "notification");
                builder.setContentTitle("COD loadout");
                builder.setContentText("You Have Added A Weapon");
                builder.setSmallIcon(R.drawable.baseline_notifications_active_24);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AdminMetas.this);
                managerCompat.notify(1,builder.build());
            }
        });

        autoCompleteTextView = findViewById(R.id.AMdorpDown);

        List<Metas> metaTitleList = metasDAO.getTitleMeta();
        List<String> listOfWeapons = new ArrayList<String>();
        for (Metas weapon : metaTitleList) {
            listOfWeapons.add(weapon.getTitleMeta() + ": " + weapon.getPrimaryWeapon() + " - " + weapon.getSecondaryWeapon());
        }

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_admin_metas, listOfWeapons);

        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String loadout = autoCompleteTextView.getAdapter().getItem(position).toString();
                int index = loadout.indexOf(":");
                String finalString = loadout.substring(0,index);
                selectedWeapon = metasDAO.getTitleMetasByTitleMetas(finalString);
            }
        });

        MaterialButton removeMetas = (MaterialButton) findViewById(R.id.EMremoveMetabtn);

        removeMetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metasDAO.delete(selectedWeapon);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(AdminMetas.this, "notification");
                builder.setContentTitle("COD loadout");
                builder.setContentText("You Have Remove A Weapon");
                builder.setSmallIcon(R.drawable.baseline_notifications_active_24);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AdminMetas.this);
                managerCompat.notify(1,builder.build());
            }
        });

    }
    private void getDatabase() {
        metasDAO = Room.databaseBuilder(this, DataBase.class, DataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .MetasDAO();
    }
}