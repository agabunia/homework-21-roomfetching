package com.example.homework_21_roomfetch.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homework_21_roomfetch.data.local.entity.ClothesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClothesDao {

    @Query("SELECT * FROM clothesentity")
    fun getAll(): Flow<List<ClothesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(clothes: List<ClothesEntity>)

    @Delete
    suspend fun delete(clothes: List<ClothesEntity>)

}
