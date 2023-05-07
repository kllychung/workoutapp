package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private var binding: ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolBarHistory)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "HISTORY"
        }

        binding?.toolBarHistory?.setNavigationOnClickListener{
            onBackPressed()
        }

        val historyDao = (application as HistoryApp).db.historyDao()
        getAllDates(historyDao)
    }

    private fun getAllDates(historyDao: HistoryDao){
        lifecycleScope.launch(){
            historyDao.fetchAllHistory().collect(){ allCompletedDates->
                if (allCompletedDates!= null){
                    val datesList = ArrayList<HistoryEntity>(allCompletedDates)

                    val historyAdapter = HistoryAdapter(datesList)

                    binding?.rvHistory?.adapter = historyAdapter
                    binding?.rvHistory?.layoutManager =
                        LinearLayoutManager(this@HistoryActivity,
                            LinearLayoutManager.VERTICAL, false)
                    binding?.rvHistory?.visibility = View.VISIBLE
                }
                else {
                    binding?.rvHistory?.visibility = View.GONE
                    binding?.tvHistoryLabel?.visibility = View.INVISIBLE
                    binding?.tvNoDataAvailable?.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}