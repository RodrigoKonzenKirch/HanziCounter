package com.example.hanzicounter.domain

import com.example.hanzicounter.data.Text
import kotlinx.coroutines.flow.Flow

interface TextRepository {
    suspend fun currentText(): Flow<Text>

    suspend fun updateCurrentText(newText: String)

}
