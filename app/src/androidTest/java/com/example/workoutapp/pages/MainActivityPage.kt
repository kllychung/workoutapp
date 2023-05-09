package com.example.workoutapp.pages

import androidx.test.espresso.ViewInteraction
import com.example.workoutapp.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId

class MainActivityPage {

    companion object {
        var btnStart : ViewInteraction = onView(withId(R.id.flStart))
        val btnBMI : ViewInteraction = onView(withId(R.id.flBmi))
        val btnHistory : ViewInteraction = onView(withId(R.id.flHistory))
    }
}