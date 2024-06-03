package com.example.hanzicounter.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hanzicounter.di.DispatcherIo
import com.example.hanzicounter.domain.TextRepository
import com.example.hanzicounter.utilities.countCharactersOccurrences
import com.example.hanzicounter.utilities.filterChineseJapaneseCharacters
import com.example.hanzicounter.utilities.highlightChars
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TextReadModeViewModel @Inject constructor(
    private val repository: TextRepository,
    @DispatcherIo private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private val _charToHighlight = MutableStateFlow('说')
    val charToHighlight: StateFlow<Char> = _charToHighlight

    private val _highlightedText = MutableStateFlow(AnnotatedString(""))
    val highlightedText: StateFlow<AnnotatedString> = _highlightedText

    private val _charsAndCounter = MutableStateFlow<List<Pair<Char, Int>>>(emptyList())
    val charsAndCounter: StateFlow<List<Pair<Char, Int>>> = _charsAndCounter

    init {
        viewModelScope.launch(dispatcherIo){
            repository.currentText().collect { text ->
                if (text.content.isNotBlank()){
                    _highlightedText.value = highlightChars(text.content, _charToHighlight.value, Color.Blue, Color.LightGray)
                    _charsAndCounter.value = text.content
                        .filterChineseJapaneseCharacters()
                        .countCharactersOccurrences()
                        .sortedByDescending { it.second }
                } else {
                    _highlightedText.value = AnnotatedString("")
                }
            }
        }
    }

    /**
     * Highlights the given character in the current text.
     *
     * @param char The character to highlight.
     */
    fun highlightChar(char: Char){
        setCharToHighlight(char)

        _highlightedText.value = highlightChars(highlightedText.value.toString(), _charToHighlight.value, Color.Blue, Color.LightGray)
    }

    /**
     * Sets the character to highlight.
     *
     * @param charToHighlight The character to highlight.
     */
    fun setCharToHighlight(charToHighlight: Char){
        _charToHighlight.value = charToHighlight
    }

    /**
     * Updates the current text in the repository, launching a coroutine on the IO dispatcher.
     *
     * @param newText The new text to replace the current text.
     */
    fun updateText(newText: String){
        viewModelScope.launch(dispatcherIo) {
            repository.updateCurrentText(newText)
        }
    }
}
