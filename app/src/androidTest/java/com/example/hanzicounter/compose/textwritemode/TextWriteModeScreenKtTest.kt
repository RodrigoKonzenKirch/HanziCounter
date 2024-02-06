package com.example.hanzicounter.compose.textwritemode

import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.hanzicounter.TextActivity
import com.example.hanzicounter.ui.theme.HanziCounterTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TextWriteModeScreenKtTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<TextActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            HanziCounterTheme {
                TextWriteModeScreen(
                    onNavigateToTextReadScreen = { },
                    onNavigateBack = { },
                    modifier = Modifier
                )
            }
        }
    }

    @Test
    fun textWriteModeScreenElementsAreShowingCorrectly(){
        composeTestRule.onNodeWithText("Save").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cancel").assertIsDisplayed()
        composeTestRule.onNodeWithText("Clear").assertIsDisplayed()
        composeTestRule.onNodeWithText("Load current text").assertIsDisplayed()
        composeTestRule.onNodeWithTag("write_screen").assertExists()
        composeTestRule.onNodeWithTag("write_mode_edit_text").assertIsDisplayed()
    }

    @Test
    fun whenClearButtonIsClicked_ClearsTheTextOfTextField() {
        composeTestRule.onNodeWithTag("write_mode_edit_text").performTextInput("Hi")
        composeTestRule.onNodeWithTag("write_mode_edit_text").assertTextContains("Hi")
        composeTestRule.onNodeWithText("Clear").performClick()
        composeTestRule.onNodeWithTag("write_mode_edit_text").assertTextEquals("")
    }


}