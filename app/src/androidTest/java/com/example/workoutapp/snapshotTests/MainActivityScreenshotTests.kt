package com.example.workoutapp.snapshotTests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.workoutapp.MainActivity
import com.shopify.testify.ScreenshotRule
import com.shopify.testify.annotation.ScreenshotInstrumentation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityScreenshotTests {
    @get:Rule
    var rule = ScreenshotRule(MainActivity::class.java)

    @ScreenshotInstrumentation
    @Test
    fun default() {
        rule.assertSame()
    }
}