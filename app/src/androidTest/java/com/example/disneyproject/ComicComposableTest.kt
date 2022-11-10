package com.example.disneyproject

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.disneyproject.model.Comic
import com.example.disneyproject.ui.theme.ProjectTheme
import com.example.disneyproject.view.ComicComposable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ComicComposableTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * A basic test to show our data is showing as expected in the composable.
     *
     * TODO: I am not sure how to validate that the glide image is loaded in a test.
     */
    @Test
    fun comicComposableTest() {
        composeTestRule.setContent {
            ProjectTheme {
                ComicComposable(comic = Comic(title = "Spiderman", description = "Does whatever a spider can!"))
            }
        }
        
        composeTestRule.onNodeWithText("Spiderman").assertIsDisplayed()
        composeTestRule.onNodeWithText("Does whatever a spider can!").assertIsDisplayed()
    }
}