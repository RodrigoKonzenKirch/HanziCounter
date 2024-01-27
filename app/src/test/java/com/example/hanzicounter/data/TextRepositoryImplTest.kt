package com.example.hanzicounter.data

import com.example.hanzicounter.domain.TextRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class TextRepositoryImplTest {

    private val textDao = mockk<TextDao>(relaxed = true)
    private lateinit var repository: TextRepository

    @Before
    fun setUp(){
        repository = TextRepositoryImpl(textDao)
    }

    @Test
    fun `getCurrentText returns current text from Dao`() = runTest {
        val expectedText = Text(1, "current", "Test text")

        every { textDao.getTextByTitle("current") } returns flowOf(expectedText)

        val actualText = repository.currentText().first()

        assertThat(actualText).isEqualTo(expectedText)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `updateCurrentText inserts new text with current title`() = runTest(UnconfinedTestDispatcher()) {
        val newText = "Updated text"

        repository.updateCurrentText(newText)

        coVerify { textDao.insertText(Text(0, "current", newText)) }
    }


}