package com.example.codloadoutproject;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.codloadoutproject.DB.DataBase;

@Entity(tableName = DataBase.METAS_TABLE)
public class Metas {
    @PrimaryKey(autoGenerate = true)
    private int metasID;
    private String titleMeta;
    private String primaryWeapon;
    private String secondaryWeapon;

    public Metas(String titleMeta, String primaryWeapon, String secondaryWeapon) {
        this.titleMeta = titleMeta;
        this.primaryWeapon = primaryWeapon;
        this.secondaryWeapon = secondaryWeapon;
    }

    @Override
    public String toString() {
        return "Metas{" +
                "metasID=" + metasID +
                ", titleMeta='" + titleMeta + '\'' +
                ", primaryWeapon='" + primaryWeapon + '\'' +
                ", secondaryWeapon='" + secondaryWeapon + '\'' +
                '}';
    }

    public String getTitleMeta() {
        return titleMeta;
    }

    public void setTitleMeta(String titleMeta) {
        this.titleMeta = titleMeta;
    }

    public int getMetasID() {
        return metasID;
    }

    public void setMetasID(int metasID) {
        this.metasID = metasID;
    }

    public String getPrimaryWeapon() {
        return primaryWeapon;
    }

    public void setPrimaryWeapon(String primaryWeapon) {
        this.primaryWeapon = primaryWeapon;
    }

    public String getSecondaryWeapon() {
        return secondaryWeapon;
    }

    public void setSecondaryWeapon(String secondaryWeapon) {
        this.secondaryWeapon = secondaryWeapon;
    }
}
