package com.example.hanzicounter.utilities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TextUtils {

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
}
