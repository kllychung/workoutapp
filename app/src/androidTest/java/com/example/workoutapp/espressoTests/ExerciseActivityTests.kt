package com.example.workoutapp.espressoTests

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.workoutapp.ExerciseActivity
import com.example.workoutapp.pages.ExercisePage
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class ExerciseActivityTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ExerciseActivity::class.java)

    @Test
    fun modalIsDisplayedAfterBackButtonClick(){
        ExercisePage.backButton.perform(click())
        ExercisePage.dialogHeaderText.check(matches(isDisplayed()));
        ExercisePage.dialogBtnNo.check(matches(isDisplayed()));
        ExercisePage.dialogBtnYes.check(matches(isDisplayed()));
    }

    @Test
    fun homePageDisplayedWhenClickingYesButtonInModal(){
        ExercisePage.backButton.perform(click())
        ExercisePage.dialogBtnYes.perform(click())
        assertTrue("Clicking on yes terminates exerciseActivity",
            activityRule.scenario.state==Lifecycle.State.DESTROYED)
    }

    @Test
    fun homePageDisplayedWhenClickingNoButtonInModal(){
        ExercisePage.backButton.perform(click())
        ExercisePage.dialogBtnNo.perform(click())
        ExercisePage.toolBarExercise.check(matches(isDisplayed()));
    }
}