package com.example.workoutapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
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

    @Test
    fun verifyButtonsAreDisplayed() {
        onView(withText("START")).check(matches(isDisplayed()))
        onView(withText("BMI")).check(matches(isDisplayed()))
        onView(withContentDescription("calendar")).check(matches(isDisplayed()))
    }

    @Test
    fun verifyButtonsAreClickable() {
        onView(withId(R.id.flBmi)).check(matches(isClickable()))
        onView(withId(R.id.flHistory)).check(matches(isClickable()))
        onView(withId(R.id.flStart)).check(matches(isClickable()))
    }

    @Test
    fun userCanNavigateToBMICalendarActivity() {
        onView(withId(R.id.flBmi)).perform(click())
        onView(withId(R.id.toolBarBmiActivity)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun userCanNavigateToExerciseActivity() {
        onView(withId(R.id.flStart)).perform(click())
        onView(withId(R.id.toolBarExercise)).check(matches(isDisplayed()))
    }

    @Test
    fun userCanNavigateToHistoryActivity() {
        onView(withId(R.id.flHistory)).perform(click())
        onView(withId(R.id.toolBarHistory)).check(matches(isDisplayed()))
    }
}