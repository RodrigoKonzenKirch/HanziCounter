package com.example.hanzicounter.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "texts", indices = [Index(value = ["title"], unique = true)])
data class Text(
    @PrimaryKey @ColumnInfo(name = "id") val textId: Int,
    val title: String,
    val content: String
)
