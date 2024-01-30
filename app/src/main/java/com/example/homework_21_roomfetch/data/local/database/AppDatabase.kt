package com.example.homework_21_roomfetch.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework_21_roomfetch.data.local.dao.ClothesDao
import com.example.homework_21_roomfetch.data.local.entity.ClothesEntity

@Database(entities = [ClothesEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clothesDao(): ClothesDao
}