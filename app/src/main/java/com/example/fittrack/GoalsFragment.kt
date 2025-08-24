package com.yourname.fittrack

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class GoalsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        // Find the Add Goal button
        val btnAddGoal = view.findViewById<Button>(R.id.btnAddGoal)

        // Set click listener for the Add Goal button
        btnAddGoal.setOnClickListener {
            // Navigate to EmptyActivity
            val intent = Intent(requireContext(), EmptyActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}