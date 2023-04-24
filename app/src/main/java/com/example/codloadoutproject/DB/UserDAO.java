package com.example.codloadoutproject.DB;

import com.example.codloadoutproject.User;

import java.util.List;

public interface UserDAO {
    @Insert
    void insert(User... Users);

    @Update
    void update(User... Users);

    @Delete
    void delete(User... Users);

    @Query("SELECT * FROM " + UserDatabase.USER_TABLE + " ORDER BY mDate desc")
    List<User> getUsername();

    @Query("SELECT * FROM " + UserDatabase.USER_TABLE + " WHERE mLogID = :logId")
    List<User> getPassword(int logId);
}
