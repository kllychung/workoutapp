package com.example.workoutapp.pages

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers
import com.example.workoutapp.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId

class ExercisePage {

    companion object{
        val backButton: ViewInteraction =
            onView(withContentDescription("Navigate up"))
        val dialogBtnYes: ViewInteraction = onView(withId(R.id.btnYes))
        val dialogBtnNo: ViewInteraction = onView(withId(R.id.btnNo))
        val toolBarExercise: ViewInteraction =
            onView(withId(R.id.toolBarExercise))
        val dialogHeaderText: ViewInteraction =
            onView(ViewMatchers.withText("Are you sure ?"))
    }

}