package com.example.hanzicounter.viewmodels

import com.example.hanzicounter.MainCoroutineRule
import com.example.hanzicounter.domain.TextRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TextReadModeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val myTestScheduler = TestCoroutineScheduler()
    private val testDispatcher = UnconfinedTestDispatcher(myTestScheduler)

    private val repositoryMock = mockk<TextRepository>(relaxed = true)
    private lateinit var viewModel: TextReadModeViewModel

    @Before
    fun setUp() {
        viewModel = TextReadModeViewModel(repositoryMock, testDispatcher)
    }

    @Test
    fun `passing string argument to updateText triggers the repository function`() = runTest{
        val text = "Hello"
        coEvery { repositoryMock.updateCurrentText(any()) } just runs

        viewModel.updateText(text)
        coVerify { repositoryMock.updateCurrentText(any()) }
    }

    @Test
    fun `call setCharToHighlight with char an argument`(){
        val char = 'H'

        assertThat(viewModel.charToHighlight.value).isNotEqualTo(char)
        viewModel.setCharToHighlight(char)
        assertThat(viewModel.charToHighlight.value).isEqualTo(char)
    }

}