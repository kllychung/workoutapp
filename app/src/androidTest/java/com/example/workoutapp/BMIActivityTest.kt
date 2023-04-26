package com.example.workoutapp


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class BMIActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(BMIActivity::class.java)

    @Test
    fun userCanSeeDefaultSettings() {
        onView(withId(R.id.toolBarBmiActivity)).check(matches(isDisplayed()))
        onView(withId(R.id.llUsHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.llDisplayBMIResult)).check(matches(not(isDisplayed())))
        onView(withId(R.id.rbMetricUnits)).check(matches(isChecked()))
        onView(withId(R.id.btnCalculate)).check(matches(isClickable()))
    }

    @Test
    fun userCalculateBMIFromMetricUnits() {
        onView(withId(R.id.et_etMetricHeight)).perform(typeText("180"))
        onView(withId(R.id.et_etWeight)).perform(typeText("60"))
        onView(withId(R.id.btnCalculate)).perform(click())
        onView(withId(R.id.llDisplayBMIResult)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.tvBMIValue)).check(matches(withText("18.52")))
        onView(withId(R.id.tvBMIType)).check(matches(withText("Normal")))
    }

    @Test
    fun userCalculateBMIFromEnglishUnits() {
        onView(withId(R.id.rbUSUnits)).perform(click())
        onView(withId(R.id.llDisplayBMIResult)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
        onView(withId(R.id.et_etWeight)).perform(typeText("140"))
        onView(withId(R.id.et_etInchesHeight)).perform(typeText("0"))
        onView(withId(R.id.et_etFeetHeight)).perform(typeText("6"))
        onView(withId(R.id.btnCalculate)).perform(click())
        onView(withId(R.id.llDisplayBMIResult)).check(matches(isDisplayed()))
        onView(withId(R.id.tvBMIValue)).check(matches(withText("18.99")))
        onView(withId(R.id.tvBMIType)).check(matches(withText("Normal")))
    }
}