package com.example.hanzicounter

import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<TextActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavGraph(modifier = Modifier, navController = navController)        }
    }

    @Test
    fun appNavHost_verifyStartDestination(){

        composeTestRule.onNodeWithTag("read_screen")
            .assertExists()
    }

    @Test
    fun navigateFromReadModeToWriteModeScreen() {
        composeTestRule.onNodeWithContentDescription("Edit Text").performClick()
        composeTestRule.onNodeWithTag("write_screen").assertExists()
    }

    @Test
    fun navigateFromReadModeToWriteMode_WhenClickCancelButtonShouldReturnToReadMode() {
        composeTestRule.onNodeWithTag("read_screen").assertExists()
        composeTestRule.onNodeWithContentDescription("Edit Text").performClick()
        composeTestRule.onNodeWithTag("read_screen").assertDoesNotExist()
        composeTestRule.onNodeWithText("Cancel").performClick()
        composeTestRule.onNodeWithTag("read_screen").assertExists()
    }
}