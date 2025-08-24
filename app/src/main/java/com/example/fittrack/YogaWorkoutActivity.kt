
package com.yourname.fittrack

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class YogaWorkoutActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var btnStartStop: Button
    private lateinit var btnReset: Button
    private lateinit var currentPoseText: TextView
    private lateinit var instructionText: TextView
    private var countDownTimer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 60000 // 60 seconds per pose
    private var isTimerRunning = false
    private var currentPoseIndex = 0

    private val yogaPoses = listOf(
        "Mountain Pose",
        "Downward Dog",
        "Warrior I",
        "Warrior II",
        "Tree Pose",
        "Child's Pose",
        "Cat-Cow Stretch",
        "Savasana"
    )

    private val poseInstructions = listOf(
        "Stand tall, feet together, arms at sides",
        "Hands and feet on ground, hips up",
        "Step left foot forward, arms overhead",
        "Arms parallel to ground, strong stance",
        "Balance on right foot, left foot on thigh",
        "Kneel down, sit back, arms forward",
        "On hands and knees, arch and round spine",
        "Lie flat, completely relaxed, eyes closed"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yoga_workout)

        // Setup toolbar with back navigation
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Yoga Flow"

        // Initialize views
        timerText = findViewById(R.id.timerText)
        btnStartStop = findViewById(R.id.btnStartStop)
        btnReset = findViewById(R.id.btnReset)
        currentPoseText = findViewById(R.id.currentPoseText)
        instructionText = findViewById(R.id.instructionText)

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
                // Move to next pose
                currentPoseIndex++
                if (currentPoseIndex < yogaPoses.size) {
                    timeLeftInMillis = 60000 // Reset to 60 seconds
                    updateUI()
                    startTimer() // Auto start next pose
                } else {
                    // Yoga session complete
                    isTimerRunning = false
                    btnStartStop.text = "Start"
                    currentPoseText.text = "Namaste 🙏"
                    instructionText.text = "Your yoga practice is complete. Well done!"
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
        currentPoseIndex = 0
        timeLeftInMillis = 60000 // Reset to 60 seconds
        isTimerRunning = false
        btnStartStop.text = "Start"
        updateUI()
    }

    private fun updateTimerText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        timerText.text = String.format("%02d:%02d", minutes, seconds)
    }

    private fun updateUI() {
        updateTimerText()
        if (currentPoseIndex < yogaPoses.size) {
            currentPoseText.text = yogaPoses[currentPoseIndex]
            instructionText.text = poseInstructions[currentPoseIndex]
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