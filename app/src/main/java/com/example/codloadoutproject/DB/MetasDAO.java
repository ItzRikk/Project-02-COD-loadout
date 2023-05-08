package com.example.codloadoutproject.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.codloadoutproject.Metas;

import java.util.List;

@Dao
public interface MetasDAO {
    @Insert
    void insert(Metas... Metas);

    @Update
    void update(Metas... Metas);

    @Delete
    void delete(Metas... Metas);

    @Query("SELECT * FROM " + DataBase.METAS_TABLE + " WHERE metasID = :metasID ")
    Metas getMetasById(int metasID);

    @Query("SELECT * FROM " + DataBase.METAS_TABLE + " WHERE titleMeta = :titleMeta ")
    Metas getTitleMetasByTitleMetas(String titleMeta);

    @Query("SELECT * FROM " + DataBase.METAS_TABLE + " WHERE primaryWeapon = :primaryWeapon ")
    Metas getPrimaryWeaponByPrimaryWeapon(String primaryWeapon);

    @Query("SELECT * FROM " + DataBase.METAS_TABLE + " WHERE secondaryWeapon = :secondaryWeapon ")
    Metas getSecondaryWeaponBySecondaryWeapon(String secondaryWeapon);

    @Query("SELECT * FROM " + DataBase.METAS_TABLE + " ORDER BY titleMeta desc")
    List<Metas> getTitleMeta();

    @Query("SELECT * FROM " + DataBase.METAS_TABLE + " ORDER BY primaryWeapon desc")
    List<Metas> getPrimaryWeapon();

    @Query("SELECT * FROM " + DataBase.METAS_TABLE + " ORDER BY secondaryWeapon desc")
    List<Metas> getSecondaryWeapon();
}
