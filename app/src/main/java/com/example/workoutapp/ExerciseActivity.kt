package com.example.workoutapp

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.ActivityExerciseBinding
import com.example.workoutapp.databinding.DialogCustomBackConfirmationBinding
import org.w3c.dom.Text
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding: ActivityExerciseBinding? = null
    private var tts:TextToSpeech? = null
    private var player: MediaPlayer? = null

    private var restTimer: CountDownTimer? = null
    private var exerciseTimer: CountDownTimer? = null

    private var restProgress: Int = 0
    private var exerciseRestProgress: Int = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePos = -1
    private var exerciseStatusAdapter: ExerciseStatusAdapter? = null

    private var restTimerDuration: Long = 10
    private var exerciseTimerDuration: Long = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolBarExercise)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        exerciseList = Constants.defaultExerciseList();

        binding?.toolBarExercise?.setNavigationOnClickListener{
            customDialogForBackButton()
        }

        tts = TextToSpeech(this, this)

        setupRestView()
        setupExerciseStatusRecyclerView()
    }

    private fun setupExerciseStatusRecyclerView(){
        binding?.rvExerciseStatus?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        exerciseStatusAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter = exerciseStatusAdapter
    }

    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress
        restTimer = object : CountDownTimer(restTimerDuration * 1000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePos++
                setupExerciseView()
                exerciseList!![currentExercisePos].setIsSelected(true)
                exerciseStatusAdapter!!.notifyDataSetChanged()
            }

        }.start()
    }

    private fun setExerciseRestProgressBar(){
        binding?.exerciseProgressBar?.progress = exerciseRestProgress
        exerciseTimer = object : CountDownTimer(exerciseTimerDuration* 1000, 1000){
            override fun onTick(p0: Long) {
                exerciseRestProgress++
                binding?.exerciseProgressBar?.progress = 30 - exerciseRestProgress
                binding?.tvExerciseTimer?.text = (30 - exerciseRestProgress).toString()
            }

            override fun onFinish() {
                if (currentExercisePos < exerciseList?.size!! - 1) {
                    exerciseList!![currentExercisePos].setIsSelected(false)
                    exerciseList!![currentExercisePos].setIsCompleted(true)
                    exerciseStatusAdapter!!.notifyDataSetChanged()
                    setupRestView()
                } else {
                    finish()
                    val intent = Intent(this@ExerciseActivity, Finish_Activity::class.java)
                    startActivity(intent)
                }
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseRestProgress = 0
        }

        if (player!= null){
            player!!.stop()
        }

        binding = null
    }

    private fun setupRestView(){

        try {
            val soundURI =
                Uri.parse("android.resource://com.example.workoutapp/"+ R.raw.press_start)
            player = MediaPlayer.create(this, soundURI)
            player?.isLooping = false
            player?.start()
        }
        catch (e: Exception){
            e.printStackTrace()
        }

        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvExerciseNamePromptHeader?.visibility = View.VISIBLE
        binding?.tvExerciseNamePrompt?.visibility = View.VISIBLE
        if ( currentExercisePos + 1 < exerciseList?.size!! ){
            binding?.tvExerciseNamePrompt?.text = exerciseList!![currentExercisePos + 1].getName()
        }
        binding?.tvTITLE?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE

        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        setRestProgressBar()
    }

    private fun setupExerciseView(){
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvExerciseNamePromptHeader?.visibility = View.INVISIBLE
        binding?.tvExerciseNamePrompt?.visibility = View.INVISIBLE
        binding?.tvTITLE?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE

        if (exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseRestProgress = 0
        }

        speakOut(exerciseList!![currentExercisePos].getName())

        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePos].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePos].getName()

        setExerciseRestProgressBar()
    }

    override fun onInit(status: Int) {
       if (status == TextToSpeech.SUCCESS) {
           var result = tts?.setLanguage(Locale.US)

           if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
               Log.e("TTS", "Language is unsupported")
           }
       }
       else {
           Log.e("TTS", "Initialization failed")
       }
    }

    private fun speakOut(text: String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun customDialogForBackButton(){
        var customDialog = Dialog(this)
        val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding?.root)
        customDialog.setCanceledOnTouchOutside(false)

        dialogBinding?.btnYes.setOnClickListener {
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }

        dialogBinding?.btnNo.setOnClickListener {
            customDialog.dismiss()
        }

        customDialog.show()
    }

}