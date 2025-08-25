package com.yourname.fittrack

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.util.Log

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Start Workout Button
        val btnStartWorkout = view.findViewById<Button>(R.id.btnStartWorkout)
        btnStartWorkout.setOnClickListener {
            try {
                val intent = Intent(requireContext(), WorkoutActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("HomeFragment", "Navigation error: ${e.message}")
            }
        }

        // Profile Image Click Listener - Navigate to ProfileFragment
        val profileImageView = view.findViewById<ImageView>(R.id.ivProfile)
        profileImageView.setOnClickListener {
            try {
                // Navigate to ProfileFragment using fragment transactiwon
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ProfileFragment())
                    .addToBackStack(null)
                    .commit()
            } catch (e: Exception) {
                Log.e("HomeFragment", "Profile navigation error: ${e.message}")
            }
        }

        return view
    }
}