package com.example.codloadoutproject.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.codloadoutproject.Metas;
import com.example.codloadoutproject.News;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NewsDAO {
    @Insert
    void insert(News... News);

    @Update
    void update(News... News);

    @Delete
    void delete(News... News);

    @Query("SELECT * FROM " + DataBase.CODNEWS_TABLE + " WHERE newsID = :newsID ")
    News getNewsById(int newsID);

    @Query("SELECT * FROM " + DataBase.CODNEWS_TABLE + " WHERE title = :title")
    News getTitleByTitle(String title);

    @Query("SELECT * FROM " + DataBase.CODNEWS_TABLE + " WHERE body = :body")
    News getBodyByBody(String body);

    @Query("SELECT * FROM " + DataBase.CODNEWS_TABLE + " ORDER BY title desc")
    List<News> getTitleNews();

}
