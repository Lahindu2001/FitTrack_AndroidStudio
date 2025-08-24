package com.yourname.fittrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class OnboardingFragment : Fragment() {

    companion object {
        private const val ARG_POSITION = "position"

        fun newInstance(position: Int): OnboardingFragment {
            val fragment = OnboardingFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)

        val position = arguments?.getInt(ARG_POSITION) ?: 0

        val imageView = view.findViewById<ImageView>(R.id.onboardingImage)
        val titleText = view.findViewById<TextView>(R.id.onboardingTitle)
        val descText = view.findViewById<TextView>(R.id.onboardingDescription)

        when (position) {
            0 -> {
                imageView.setImageResource(R.drawable.ic_workout)
                titleText.text = getString(R.string.onboarding_title_1)
                descText.text = getString(R.string.onboarding_desc_1)
            }
            1 -> {
                imageView.setImageResource(R.drawable.ic_goals)
                titleText.text = getString(R.string.onboarding_title_2)
                descText.text = getString(R.string.onboarding_desc_2)
            }
            2 -> {
                imageView.setImageResource(R.drawable.ic_tips)
                titleText.text = getString(R.string.onboarding_title_3)
                descText.text = getString(R.string.onboarding_desc_3)
            }
        }

        return view
    }
}