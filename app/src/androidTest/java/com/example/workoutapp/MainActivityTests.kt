package com.example.workoutapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@MediumTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    companion object {
        var btnStart : ViewInteraction = onView(withId(R.id.flStart))
        val btnBMI : ViewInteraction = onView(withId(R.id.flBmi))
        val btnHistory : ViewInteraction = onView(withId(R.id.flHistory))
    }

    @Test
    fun verifyTextDisplay() {
        onView(withText("START")).check(matches(isDisplayed()))
        onView(withText("BMI")).check(matches(isDisplayed()))
        onView(withContentDescription("calendar")).check(matches(isDisplayed()))
    }

    @Test
    fun verifyButtonsAreClickable() {
        btnBMI.check(matches(isClickable()))
        btnHistory.check(matches(isClickable()))
        btnStart.check(matches(isClickable()))
    }

    @Test
    fun userCanNavigateToBMICalendarActivity() {
        btnBMI.perform(click())
        BMIActivityTests.toolBarBmiActivity.check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun userCanNavigateToExerciseActivity() {
        btnStart.perform(click())
        ExerciseActivityTests.toolBarExercise.check(matches(isDisplayed()))
    }

    @Test
    fun userCanNavigateToHistoryActivity() {
        btnHistory.perform(click())
        HistoryActivityTests.toolBarHistory.check(matches(isDisplayed()))
    }
}