package com.example.workoutapp.espressoTests

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.workoutapp.*
import com.example.workoutapp.pages.HistoryPage
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.*
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class HistoryActivityTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(HistoryActivity::class.java)

    private lateinit var db: HistoryDatabase
    private lateinit var historyDao: HistoryDao
    private val date = "24 JAN 2019 12:01:03"

    @Before
    fun setup(){
        db = (ApplicationProvider.getApplicationContext<Context>() as HistoryApp).db
        historyDao = db.historyDao()
        removeData()
    }

    @Test
    fun verifyDateHistoryIsDisplayed(){
        HistoryPage.rvHistory.check(matches(not(isDisplayed())))
        insertData()
        HistoryPage.tvHistoryLabel.check(matches(isDisplayed()))
        HistoryPage.rvHistory.check(matches(isDisplayed()))
        onView(withText(date)).check(matches(isDisplayed()))
    }

    @After
    fun clearData(){
        removeData()
    }

    private fun insertData() = runTest {
        val job = launch {
            historyDao.insert(HistoryEntity(date))
        }
        job.join()
    }

    private fun removeData() = runTest {
        val job = launch {
            historyDao.delete(HistoryEntity(date))
        }
        job.join()
    }
}