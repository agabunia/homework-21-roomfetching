package com.example.homework_21_roomfetch.data.local.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.homework_21_roomfetch.data.local.dao.ClothesDao
import com.example.homework_21_roomfetch.data.local.entity.ClothesEntity

//@Database(entities = [ClothesEntity::class], version = 1, exportSchema = true)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun clothesDao(): ClothesDao
//}

@Database(
    entities = [ClothesEntity::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clothesDao(): ClothesDao

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE ClothesEntity ADD COLUMN category TEXT DEFAULT NULL")
            }
        }
    }
}