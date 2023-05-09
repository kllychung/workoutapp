package com.example.workoutapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class HistoryDaoTest {

    private lateinit var db: HistoryDatabase
    private lateinit var historyDao: HistoryDao

    @Before
    fun setup(){
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
            HistoryDatabase::class.java).build()
        historyDao = db.historyDao()
    }

    @Test
    fun fetchAllHistory() = runTest{
        val testDates = arrayOf("23 JAN 2019 12:01:03", "24 JAN 2019 12:01:03",
            "25 JAN 2019 12:01:03")
        var actualCount: Int = 0;
        val job = launch {
            testDates.map { date -> historyDao.insert(HistoryEntity(date)) }
            actualCount = historyDao.fetchAllHistory().first().size
        }
        job.join()
        assertEquals(testDates.size, actualCount)
    }

    @Test
    fun addDate() = runTest{
        val date = "20 JAN 2009 12:01:03"
        val dateEntity = HistoryEntity(date)
        var retrievedDate: String = "";
        val job = launch {
            historyDao.insert(dateEntity)
            retrievedDate = historyDao.fetchHistoryByDate(date).first().date
        }
        job.join()
        assertEquals(date, retrievedDate)
    }

    @Test
    fun deleteDate() = runTest{
        val testDates = arrayOf("25 JAN 2019 12:01:03",
            "26 JAN 2019 12:01:03")
        var actualData = listOf<HistoryEntity>()
        val job = launch {
            testDates.map { date -> historyDao.insert(HistoryEntity(date)) }
            historyDao.delete(HistoryEntity(testDates[0]))
            actualData = historyDao.fetchAllHistory().first()
        }
        job.join()
        assertEquals(1, actualData.size)
        assertEquals(testDates[1], actualData.first().date)
    }

    @After
    fun closeDb() {
        db.close()
    }
}