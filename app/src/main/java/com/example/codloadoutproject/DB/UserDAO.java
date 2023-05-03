package com.example.codloadoutproject.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.codloadoutproject.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User... Users);

    @Update
    void update(User... Users);

    @Delete
    void delete(User... Users);

    @Query("SELECT * FROM " + DataBase.USER_TABLE + " WHERE userId = :userId ")
    User getUserById(int userId);

    @Query("SELECT * FROM " + DataBase.USER_TABLE + " WHERE username = :username")
    User getUsernameByUsername(String username);

    @Query("SELECT * FROM " + DataBase.USER_TABLE + " WHERE password = :password")
    User getPasswordbyPassword(String password);
}
