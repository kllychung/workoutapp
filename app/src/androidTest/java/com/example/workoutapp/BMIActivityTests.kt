package com.example.workoutapp


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.MediumTest
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class BMIActivityTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(BMIActivity::class.java)

    companion object {
        val toolBarBmiActivity : ViewInteraction =  onView(withId(R.id.toolBarBmiActivity))
        val btnCalculate : ViewInteraction = onView(withId(R.id.btnCalculate))
        val llBMIResults : ViewInteraction = onView(withId(R.id.llDisplayBMIResult))
        val tvBMIValue : ViewInteraction = onView(withId(R.id.tvBMIValue))
        val tvBMIType : ViewInteraction = onView(withId(R.id.tvBMIType))
        val llUsHeight : ViewInteraction =  onView(withId(R.id.llUsHeight))
        val rbMetricUnits : ViewInteraction = onView(withId(R.id.rbMetricUnits))
        val rbUSUnits : ViewInteraction = onView(withId(R.id.rbUSUnits))
        val etMetricHeight : ViewInteraction =  onView(withId(R.id.et_etMetricHeight))
        val etWeight : ViewInteraction =  onView(withId(R.id.et_etWeight))
        val etInchesHeight : ViewInteraction =  onView(withId(R.id.et_etInchesHeight))
        val et_etFeetHeight : ViewInteraction =  onView(withId(R.id.et_etFeetHeight))
    }

    @Test
    fun userCanSeeDefaultSettings() {
        toolBarBmiActivity.check(matches(isDisplayed()))
        llUsHeight.check(matches(not(isDisplayed())))
        llBMIResults.check(matches(not(isDisplayed())))
        rbMetricUnits.check(matches(isChecked()))
        btnCalculate.check(matches(isClickable()))
    }

    @Test
    fun userCalculateBMIFromMetricUnits() {
        etMetricHeight.perform(typeText("180"))
        etWeight.perform(typeText("60"))
        btnCalculate.perform(click())
        llBMIResults.check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        tvBMIValue.check(matches(withText("18.52")))
        tvBMIType.check(matches(withText("Normal")))
    }

    @Test
    fun userCalculateBMIFromEnglishUnits() {
        rbUSUnits.perform(click())
        llBMIResults.check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
        etWeight.perform(typeText("140"))
        etInchesHeight.perform(typeText("0"))
        et_etFeetHeight.perform(typeText("6"))
        btnCalculate.perform(click())
        llBMIResults.check(matches(isDisplayed()))
        tvBMIValue.check(matches(withText("18.99")))
        tvBMIType.check(matches(withText("Normal")))
    }
}