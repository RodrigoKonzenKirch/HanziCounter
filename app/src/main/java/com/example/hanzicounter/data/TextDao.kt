package com.example.hanzicounter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TextDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertText(text: Text): Long

    // get text by title
    @Query("SELECT * FROM texts WHERE title = :title")
    fun getTextByTitle(title: String): Flow<Text>

}