package com.example.workoutapp.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.workoutapp.R

class HistoryPage {
    companion object{
        val tvHistoryLabel : ViewInteraction = onView(withId(R.id.tvHistoryLabel))
        val toolBarHistory : ViewInteraction = onView(withId(R.id.toolBarHistory))
        var rvHistory : ViewInteraction = onView(withId(R.id.rv_history))
    }
}