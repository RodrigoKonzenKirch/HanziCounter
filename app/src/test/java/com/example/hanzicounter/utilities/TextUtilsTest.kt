package com.example.hanzicounter.utilities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TextUtilsTest {

    @Test
    fun `highlight single character`() {
        val text = "Hello, world!"
        val charToHighlight = 'w'
        val expected = buildAnnotatedString {
            append("Hello, ")
            withStyle(SpanStyle(color = Color.Blue, background = Color.LightGray)) {
                append("w")
            }
            append("orld!")
        }

        val actual = highlightChars(text, charToHighlight, Color.Blue, Color.LightGray)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `highlight multiple characters`() {
        val text = "Hello, world!"
        val charToHighlight = 'o'
        val expected = buildAnnotatedString {
            append("Hell")
            withStyle(SpanStyle(color = Color.Blue, background = Color.LightGray)) {
                append("o")
            }
            append(", w")
            withStyle(SpanStyle(color = Color.Blue, background = Color.LightGray)) {
                append("o")
            }
            append("rld!")
        }

        val actual = highlightChars(text, charToHighlight, Color.Blue, Color.LightGray)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `no highlight if character not present`() {
        val text = "No characters to highlight"
        val charToHighlight = 'x'
        val expected = buildAnnotatedString {
            append(text)
        }

        val actual = highlightChars(text, charToHighlight, Color.Blue, Color.LightGray)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `empty text should generate empty AnnotatedString`() {
        val text = ""
        val charToHighlight = 'a'
        val expected = buildAnnotatedString {
            // Empty AnnotatedString
        }

        val actual = highlightChars(text, charToHighlight, Color.Blue, Color.LightGray)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `count characters in simple string`() {
        val text = "simple"
        val expected = listOf('s' to 1, 'i' to 1, 'm' to 1, 'p' to 1, 'l' to 1, 'e' to 1)
        val actual = text.countCharactersOccurrences()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `count characters with duplicates`() {
        val text = "abbbcc"
        val expected = listOf('a' to 1, 'b' to 3, 'c' to 2)
        val actual = text.countCharactersOccurrences()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `empty string should return empty list of Pair of Char and Int`() {
        val text = ""
        val expected = emptyList<Pair<Char, Int>>()
        val actual = text.countCharactersOccurrences()
        assertThat(actual).isEqualTo(expected)

    }

}
