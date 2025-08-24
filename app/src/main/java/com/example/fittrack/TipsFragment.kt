package com.yourname.fittrack

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class TipsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tips, container, false)

        // Initialize buttons
        val btnCardio = view.findViewById<Button>(R.id.btnCardio)
        val btnStrength = view.findViewById<Button>(R.id.btnStrength)
        val btnNutrition = view.findViewById<Button>(R.id.btnNutrition)

        // Set click listeners
        btnCardio.setOnClickListener {
            val intent = Intent(requireContext(), CardioTipsActivity::class.java)
            startActivity(intent)
        }

        btnStrength.setOnClickListener {
            val intent = Intent(requireContext(), StrengthTipsActivity::class.java)
            startActivity(intent)
        }

        btnNutrition.setOnClickListener {
            val intent = Intent(requireContext(), NutritionTipsActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}