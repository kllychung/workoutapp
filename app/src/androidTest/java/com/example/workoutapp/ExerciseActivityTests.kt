package com.example.workoutapp

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class ExerciseActivityTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ExerciseActivity::class.java)

    companion object{
        val backButton: ViewInteraction = onView(withContentDescription("Navigate up"))
        val dialogBtnYes: ViewInteraction = onView(withId(R.id.btnYes))
        val dialogBtnNo: ViewInteraction = onView(withId(R.id.btnNo))
        val toolBarExercise: ViewInteraction= onView(withId(R.id.toolBarExercise))
        val dialogHeaderText: ViewInteraction = onView(withText("Are you sure ?"))
    }

    @Test
    fun modalIsDisplayedAfterBackButtonClick(){
        backButton.perform(click())
        dialogHeaderText.check(matches(isDisplayed()));
        dialogBtnNo.check(matches(isDisplayed()));
        dialogBtnYes.check(matches(isDisplayed()));
    }

    @Test
    fun homePageDisplayedWhenClickingYesButtonInModal(){
        backButton.perform(click())
        dialogBtnYes.perform(click())
        assertTrue("Clicking on yes terminates exerciseActivity",
            activityRule.scenario.state==Lifecycle.State.DESTROYED)
    }

    @Test
    fun homePageDisplayedWhenClickingNoButtonInModal(){
        backButton.perform(click())
        dialogBtnNo.perform(click())
        toolBarExercise.check(matches(isDisplayed()));
    }
}