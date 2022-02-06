package com.example.canteen_omics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class OthersFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_others, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val omenu = view.findViewById<Button>(R.id.omenu_button)
        omenu.setOnClickListener {
            findNavController().navigate(R.id.action_othersFragment_to_canteen_1Fragment)
        }

        val odrinks = view.findViewById<Button>(R.id.odrinks_button)
        odrinks.setOnClickListener {
            findNavController().navigate(R.id.action_othersFragment_to_drinksFragment)
        }
//
//        val C3 = view.findViewById<Button>(R.id.canteen3_button)
//        C3.setOnClickListener {
//            findNavController().navigate(R.id.action_navigation_home_to_othersFragment)
//        }
    }
}