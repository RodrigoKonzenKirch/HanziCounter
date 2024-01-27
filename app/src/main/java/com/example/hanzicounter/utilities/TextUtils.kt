package com.example.hanzicounter.utilities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString

/**
 * Creates an AnnotatedString with specific characters highlighted.
 *
 * @param text The original text to be processed.
 * @param charToHighlight The character to highlight within the text.
 * @param color Color of the highlighted characters.
 * @param background Color of the background of highlighted characters.
 * @return An AnnotatedString containing the text with highlighted characters.
 */
fun highlightChars(text: String, charToHighlight: Char, color: Color, background: Color): AnnotatedString {
    val annotatedString = buildAnnotatedString {
        append(text)

        var startIndex = text.indexOf(charToHighlight)
        while (startIndex != -1){
            val endIndex = startIndex + 1
            addStyle(
                style = SpanStyle(color = color, background = background),
                start = startIndex,
                end = endIndex
            )
            startIndex = text.indexOf(charToHighlight, endIndex)

        }
    }
    return annotatedString
}

/**
 * Counts the occurrences of each unique character in the string and returns a list of character-count pairs.
 *
 * @return A list of pairs, where each pair contains a unique character from the string and its corresponding count.
 */
fun String.countCharactersOccurrences(): List<Pair<Char, Int>> {
    val characterCounts = mutableMapOf<Char, Int>()
    for (char in this) {
        characterCounts[char] = characterCounts.getOrDefault(char, 0) + 1
    }
    return characterCounts.toList()
}