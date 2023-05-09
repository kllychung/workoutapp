package com.example.workoutapp.espressoTests

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.workoutapp.MainActivity
import com.example.workoutapp.pages.BmiCalculatorPage
import com.example.workoutapp.pages.ExercisePage
import com.example.workoutapp.pages.HistoryPage
import com.example.workoutapp.pages.MainActivityPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun userCanNavigateToBMICalendarActivity() {
        MainActivityPage.btnBMI.perform(click())
        BmiCalculatorPage.toolBarBmiActivity.check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun userCanNavigateToExerciseActivity() {
        MainActivityPage.btnStart.perform(click())
        ExercisePage.toolBarExercise.check(matches(isDisplayed()))
    }

    @Test
    fun userCanNavigateToHistoryActivity() {
        MainActivityPage.btnHistory.perform(click())
        HistoryPage.toolBarHistory.check(matches(isDisplayed()))
    }
}