package com.example.codloadoutproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.codloadoutproject.DB.DataBase;

@Entity(tableName = DataBase.CODNEWS_TABLE)
public class News {
    @PrimaryKey(autoGenerate = true)
    private int newsID;
    private String title;
    private String body;

    public News(String title, String body) {
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsID=" + newsID +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
