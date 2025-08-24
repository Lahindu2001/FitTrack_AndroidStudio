package com.yourname.fittrack

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView

class WorkoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Start Workout"

        val btnQuickStart = findViewById<Button>(R.id.btnQuickStart)
        val cardCardio = findViewById<CardView>(R.id.cardCardio)
        val cardStrength = findViewById<CardView>(R.id.cardStrength)
        val cardYoga = findViewById<CardView>(R.id.cardYoga)

        btnQuickStart.setOnClickListener {
            try {
                val intent = Intent(this, QuickStartActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("WorkoutActivity", "QuickStart navigation error: ${e.message}")
            }
        }

        cardCardio.setOnClickListener {
            try {
                val intent = Intent(this, CardioWorkoutActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("WorkoutActivity", "Cardio navigation error: ${e.message}")
            }
        }

        cardStrength.setOnClickListener {
            try {
                val intent = Intent(this, StrengthWorkoutActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("WorkoutActivity", "Strength navigation error: ${e.message}")
            }
        }

        cardYoga.setOnClickListener {
            try {
                val intent = Intent(this, YogaWorkoutActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("WorkoutActivity", "Yoga navigation error: ${e.message}")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}