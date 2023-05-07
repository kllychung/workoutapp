package com.example.workoutapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class HistoryActivityTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(HistoryActivity::class.java)

    @Test
    fun verifyDateHistoryIsDisplayed(){
        onView(withId(R.id.tvHistoryLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_history)).check(matches(not(isDisplayed())))
    }
}