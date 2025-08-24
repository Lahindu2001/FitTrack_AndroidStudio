package com.yourname.fittrack

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class StrengthWorkoutActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var btnStartStop: Button
    private lateinit var btnReset: Button
    private lateinit var currentExerciseText: TextView
    private lateinit var repsText: TextView
    private var countDownTimer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 45000 // 45 seconds per exercise
    private var isTimerRunning = false
    private var currentExerciseIndex = 0

    private val strengthExercises = listOf(
        "Push-ups",
        "Squats",
        "Lunges",
        "Plank",
        "Tricep Dips",
        "Wall Sit",
        "Crunches",
        "Glute Bridge"
    )

    private val exerciseReps = listOf(
        "10-15 reps",
        "15-20 reps",
        "10 per leg",
        "Hold position",
        "10-12 reps",
        "Hold position",
        "15-20 reps",
        "15-20 reps"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_strength_workout)

        // Setup toolbar with back navigation
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Strength Training"

        // Initialize views
        timerText = findViewById(R.id.timerText)
        btnStartStop = findViewById(R.id.btnStartStop)
        btnReset = findViewById(R.id.btnReset)
        currentExerciseText = findViewById(R.id.currentExerciseText)
        repsText = findViewById(R.id.repsText)

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
                if (currentExerciseIndex < strengthExercises.size) {
                    timeLeftInMillis = 45000 // Reset to 45 seconds
                    updateUI()
                    // Add 15 second rest period
                    pauseTimer()
                    currentExerciseText.text = "Rest - Next: ${strengthExercises[currentExerciseIndex]}"
                } else {
                    // Workout complete
                    isTimerRunning = false
                    btnStartStop.text = "Start"
                    currentExerciseText.text = "Strength Training Complete!"
                    repsText.text = "Excellent work! 💪"
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
        timeLeftInMillis = 45000 // Reset to 45 seconds
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
        if (currentExerciseIndex < strengthExercises.size) {
            currentExerciseText.text = strengthExercises[currentExerciseIndex]
            repsText.text = exerciseReps[currentExerciseIndex]
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
