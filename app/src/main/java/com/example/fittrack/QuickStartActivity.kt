package com.yourname.fittrack

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class QuickStartActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var btnStartStop: Button
    private lateinit var btnReset: Button
    private var countDownTimer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 300000 // 5 minutes in milliseconds
    private var isTimerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_start)

        // Setup toolbar with back navigation
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Quick Start Workout"

        // Initialize views
        timerText = findViewById(R.id.timerText)
        btnStartStop = findViewById(R.id.btnStartStop)
        btnReset = findViewById(R.id.btnReset)

        updateTimerText()

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
                isTimerRunning = false
                btnStartStop.text = "Start"
                // You can add completion logic here (e.g., show completion dialog)
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
        timeLeftInMillis = 300000 // Reset to 5 minutes
        isTimerRunning = false
        btnStartStop.text = "Start"
        updateTimerText()
    }

    private fun updateTimerText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        timerText.text = String.format("%02d:%02d", minutes, seconds)
    }

    // Handle toolbar back button
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true;
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}