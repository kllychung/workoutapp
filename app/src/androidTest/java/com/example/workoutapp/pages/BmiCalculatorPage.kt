package com.example.workoutapp.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.workoutapp.R

class BmiCalculatorPage {
    companion object {
        val toolBarBmiActivity : ViewInteraction = onView(withId(R.id.toolBarBmiActivity))
        val btnCalculate : ViewInteraction = onView(withId(R.id.btnCalculate))
        val llBMIResults : ViewInteraction = onView(withId(R.id.llDisplayBMIResult))
        val tvBMIValue : ViewInteraction = onView(withId(R.id.tvBMIValue))
        val tvBMIType : ViewInteraction = onView(withId(R.id.tvBMIType))
        val llUsHeight : ViewInteraction = onView(withId(R.id.llUsHeight))
        val rbMetricUnits : ViewInteraction = onView(withId(R.id.rbMetricUnits))
        private val rbUSUnits : ViewInteraction = onView(withId(R.id.rbUSUnits))
        private val etMetricHeight : ViewInteraction = onView(withId(R.id.et_etMetricHeight))
        private val etWeight : ViewInteraction = onView(withId(R.id.et_etWeight))
        private val etInchesHeight : ViewInteraction = onView(withId(R.id.et_etInchesHeight))
        private val et_etFeetHeight : ViewInteraction = onView(withId(R.id.et_etFeetHeight))

        fun calculateBMIFromMetricUnits(weight: String, height: String){
            etMetricHeight.perform(typeText(height))
            etWeight.perform(typeText(weight))
            btnCalculate.perform(ViewActions.click())
        }

        fun verifyBMIResultsAreVisible(){
            llBMIResults.check(matches(
                withEffectiveVisibility(Visibility.VISIBLE)))
        }

        fun verifyBMIResultsAreHidden(){
            llBMIResults.check(matches(
                withEffectiveVisibility(Visibility.INVISIBLE)))
        }

        fun switchToEnglishUnitsCalculator(){
            rbUSUnits.perform(ViewActions.click())
        }

        fun calculateBMIFromEnglishUnits(weight: String, heightInFeet: String, heightInInches:String){
            etWeight.perform(typeText(weight))
            etInchesHeight.perform(typeText(heightInInches))
            et_etFeetHeight.perform(typeText(heightInFeet))
            btnCalculate.perform(ViewActions.click())
        }
    }
}