package com.example.hanzicounter.data

import com.example.hanzicounter.domain.TextRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TextRepositoryImpl @Inject constructor(private val textDao: TextDao) : TextRepository {
    private val currentTextTitle = "current"
    override suspend fun getCurrentText(): Flow<Text> {
        return textDao.getTextByTitle(currentTextTitle)
    }

    override suspend fun updateCurrentText(newText: String) {
        textDao.insertText(Text(0, currentTextTitle, newText))
    }

}
