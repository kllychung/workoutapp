package com.example.workoutapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Delete
    suspend fun delete(historyEntity: HistoryEntity)

    @Update
    suspend fun update(historyEntity: HistoryEntity)

    @Query("SELECT * FROM `history-table`")
    fun fetchAllHistory(): Flow<List<HistoryEntity>>

    @Query("SELECT * FROM `history-table` where date =:date")
    fun fetchHistoryByDate(date: String):Flow<HistoryEntity>
}