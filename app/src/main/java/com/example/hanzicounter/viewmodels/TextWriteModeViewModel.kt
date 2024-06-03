package com.example.hanzicounter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hanzicounter.di.DispatcherIo
import com.example.hanzicounter.domain.TextRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TextWriteModeViewModel @Inject constructor(
    private val repository: TextRepository,
    @DispatcherIo private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private var _currentText = MutableStateFlow("")

    init {
        viewModelScope.launch(dispatcherIo) {
            repository.currentText().collect { text ->
                _currentText.value = text.content
            }
        }
    }

    /**
     * @return current text
     */
    fun getCurrentText(): String {
        return _currentText.value
    }

    /**
     * @param newText new text to save
     */
    fun saveText(newText: String) {
        viewModelScope.launch(dispatcherIo) {
            repository.updateCurrentText(newText)
        }
    }
}