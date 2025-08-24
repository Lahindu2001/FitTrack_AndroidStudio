package com.yourname.fittrack

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class NutritionTipsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrition_tips)

        // Setup toolbar with back button
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Nutrition Tips"
        }

        // Setup custom back button in layout
        val backButton = findViewById<ImageButton>(R.id.btnBack)
        backButton.setOnClickListener {
            finish() // This will navigate back to TipsFragment
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Navigate back to TipsFragment
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish() // Ensure we return to TipsFragment
    }
}