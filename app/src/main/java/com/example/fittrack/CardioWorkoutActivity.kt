package com.yourname.fittrack

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class CardioWorkoutActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var btnStartStop: Button
    private lateinit var btnReset: Button
    private lateinit var currentExerciseText: TextView
    private var countDownTimer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 30000 // 30 seconds per exercise
    private var isTimerRunning = false
    private var currentExerciseIndex = 0

    private val cardioExercises = listOf(
        "Jumping Jacks",
        "High Knees",
        "Burpees",
        "Mountain Climbers",
        "Running in Place",
        "Jump Squats",
        "Butt Kicks",
        "Star Jumps"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardio_workout)

        // Setup toolbar with back navigation
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Cardio Workout"

        // Initialize views
        timerText = findViewById(R.id.timerText)
        btnStartStop = findViewById(R.id.btnStartStop)
        btnReset = findViewById(R.id.btnReset)
        currentExerciseText = findViewById(R.id.currentExerciseText)

        updateUI()

        // Start/Stop button click listener
        btnStartStop.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        // Reset button click listener
        btnReset.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                // Move to next exercise
                currentExerciseIndex++
                if (currentExerciseIndex < cardioExercises.size) {
                    timeLeftInMillis = 30000 // Reset to 30 seconds
                    updateUI()
                    startTimer() // Auto start next exercise
                } else {
                    // Workout complete
                    isTimerRunning = false
                    btnStartStop.text = "Start"
                    currentExerciseText.text = "Workout Complete! Great job!"
                }
            }
        }.start()

        isTimerRunning = true
        btnStartStop.text = "Pause"
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        isTimerRunning = false
        btnStartStop.text = "Start"
    }

    private fun resetTimer() {
        countDownTimer?.cancel()
        currentExerciseIndex = 0
        timeLeftInMillis = 30000 // Reset to 30 seconds
        isTimerRunning = false
        btnStartStop.text = "Start"
        updateUI()
    }

    private fun updateTimerText() {
        val seconds = timeLeftInMillis / 1000
        timerText.text = String.format("%02d", seconds)
    }

    private fun updateUI() {
        updateTimerText()
        if (currentExerciseIndex < cardioExercises.size) {
            currentExerciseText.text = cardioExercises[currentExerciseIndex]
        }
    }

    // Handle toolbar back button
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}
