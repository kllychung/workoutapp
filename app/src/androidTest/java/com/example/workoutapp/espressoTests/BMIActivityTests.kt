package com.example.workoutapp.espressoTests

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.workoutapp.BMIActivity
import com.example.workoutapp.pages.BmiCalculatorPage
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class BMIActivityTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(BMIActivity::class.java)

    @Test
    fun userCanSeeDefaultSettings() {
        BmiCalculatorPage.toolBarBmiActivity.check(matches(isDisplayed()))
        BmiCalculatorPage.llUsHeight.check(matches(not(isDisplayed())))
        BmiCalculatorPage.llBMIResults.check(matches(not(isDisplayed())))
        BmiCalculatorPage.rbMetricUnits.check(matches(isChecked()))
        BmiCalculatorPage.btnCalculate.check(matches(isClickable()))
    }

    @Test
    fun userCalculateBMIFromMetricUnits() {
        BmiCalculatorPage.calculateBMIFromMetricUnits("60", "180")
        BmiCalculatorPage.verifyBMIResultsAreVisible()
        BmiCalculatorPage.tvBMIValue.check(matches(withText("18.52")))
        BmiCalculatorPage.tvBMIType.check(matches(withText("Normal")))
    }

    @Test
    fun userCalculateBMIFromEnglishUnits() {
        BmiCalculatorPage.switchToEnglishUnitsCalculator()
        BmiCalculatorPage.verifyBMIResultsAreHidden()
        BmiCalculatorPage.calculateBMIFromEnglishUnits("140", "6","0")
        BmiCalculatorPage.verifyBMIResultsAreVisible()
        BmiCalculatorPage.tvBMIValue.check(matches(withText("18.99")))
        BmiCalculatorPage.tvBMIType.check(matches(withText("Normal")))
    }
}