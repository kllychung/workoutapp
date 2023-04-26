package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.view.View
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    private var binding:ActivityBmiBinding?= null
    private var currentVisibleView: String = METRIC_UNITS_VIEW

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolBarBmiActivity)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolBarBmiActivity?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnCalculate?.setOnClickListener {
            calculateUnits()
        }

        displayMetricUnits()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId:Int ->
            if (checkedId == R.id.rbMetricUnits){
                displayMetricUnits()
            }
            else {
                displayUsUnits()
            }
        }
    }

    private fun calculateUnits(){
        if (currentVisibleView == METRIC_UNITS_VIEW){
            if (validateMetricUnits()){
                val height : Float = binding?.etEtMetricHeight?.text.toString().toFloat() / 100
                val weight : Float = binding?.etEtWeight?.text.toString().toFloat()
                val bmi : Float = weight/(height * height)
                displayBMIResults(bmi)
            }
            else {
                Toast.makeText(this@BMIActivity, "Please enter valid value",
                    Toast.LENGTH_SHORT).show()
            }
        }
        else {
            if (validateUsUnits()){
                val weight : Float = binding?.etEtWeight?.text.toString().toFloat()
                val heightInFeet : Float = binding?.etEtFeetHeight?.text.toString().toFloat()
                val heightInInches: Float = binding?.etEtInchesHeight?.text.toString().toFloat()
                val heightValueTotalInInches = heightInFeet * 12 + heightInInches
                val bmi = 703 * (weight/(heightValueTotalInInches * heightValueTotalInInches ))
                displayBMIResults(bmi)
            }
            else {
                Toast.makeText(this@BMIActivity, "Please enter valid value",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayMetricUnits() {
        binding?.rbMetricUnits?.isChecked = true
        binding?.rbUSUnits?.isChecked = false
        binding?.tilMetricHeight?.visibility = View.VISIBLE
        binding?.llUsHeight?.visibility = View.INVISIBLE
        binding?.etEtMetricHeight?.text!!.clear()
        binding?.etEtWeight?.text!!.clear()
        binding?.etEtWeight?.hint = "Weight (in kg)"
        currentVisibleView = METRIC_UNITS_VIEW
    }

    private fun displayUsUnits(){
        binding?.rbMetricUnits?.isChecked = false
        binding?.rbUSUnits?.isChecked = true
        binding?.tilMetricHeight?.visibility = View.INVISIBLE
        binding?.llUsHeight?.visibility = View.VISIBLE
        binding?.etEtInchesHeight?.text!!.clear()
        binding?.etEtFeetHeight?.text!!.clear()
        binding?.etEtWeight?.text!!.clear()
        binding?.etEtWeight?.hint = "Weight (in lbs)"
        currentVisibleView = US_UNITS_VIEW
    }

    private fun validateMetricUnits(): Boolean{
        var isValid = true
        if (binding?.etEtMetricHeight?.text.toString().isEmpty()){
            isValid = false
        }
        else if (binding?.etEtWeight?.text.toString().isEmpty()){
            isValid = false
        }
        return  isValid
    }

    private fun validateUsUnits():Boolean {
        var isValid: Boolean = true

        when{
            binding?.etEtInchesHeight?.text.toString().isEmpty()->{
                isValid = false
            }
            binding?.etEtFeetHeight?.text.toString().isEmpty()->{
                isValid = false
            }
            binding?.etEtWeight?.text.toString().isEmpty()->{
                isValid = false
            }
        }
        return isValid
    }

    private fun displayBMIResults(bmi: Float){
        val bmiLabel : String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0){
            bmiLabel  = "Very severely underweight"
            bmiDescription = "Eat more and take better care of yourself"
        }
        else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0){
            bmiLabel  = "Severely underweight"
            bmiDescription = "Eat more and take better care of yourself"
        }
        else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0){
            bmiLabel  = "Normal"
            bmiDescription = "You are in good shape"
        }
        else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0){
            bmiLabel  = "Overweight"
            bmiDescription = "Exercise More"
        }
        else if (bmi.compareTo(30f) > 0 && bmi.compareTo(40f) <= 0){
            bmiLabel  = "Severely overweight"
            bmiDescription = "Exercise More"
        }
        else {
            bmiLabel  = "Obese Class"
            bmiDescription = "Dangerous health condition"
        }

        binding?.tvBMIValue?.text = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding?.tvBMIDescription?.text = bmiDescription
        binding?.tvBMIType?.text = bmiLabel
        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
    }
}