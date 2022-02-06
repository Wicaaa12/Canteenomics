package com.example.canteen_omics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class Canteen_1Fragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_canteen_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val drinks = view.findViewById<Button>(R.id.drinks_button)
        drinks.setOnClickListener {
            findNavController().navigate(R.id.action_canteen_1Fragment_to_drinksFragment)
        }

        val others = view.findViewById<Button>(R.id.other_button)
        others.setOnClickListener {
            findNavController().navigate(R.id.action_canteen_1Fragment_to_othersFragment)
        }

    }
}