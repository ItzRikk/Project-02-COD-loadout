package com.example.codloadoutproject.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.codloadoutproject.Metas;
import com.example.codloadoutproject.News;
import com.example.codloadoutproject.User;

@Database(entities = {User.class, Metas.class, News.class}, version = 3)
//@TypeConverters({DateTypeConverter.class})
public abstract class DataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "CODLoadout.db";
    public static final String USER_TABLE = "User_table";
    public static final String CODNEWS_TABLE = "CodNews_table";
    public static final String METAS_TABLE = "Metas_table";

    private static volatile DataBase instance;
    private static final Object LOCK = new Object();

    public abstract UserDAO UserDAO();
    public abstract MetasDAO MetasDAO();
    public abstract NewsDAO NewsDAO();

    public static DataBase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
