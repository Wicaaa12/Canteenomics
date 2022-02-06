package com.example.canteen_omics

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth


class AdminFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        auth = FirebaseAuth.getInstance()
//
//        if(auth.currentUser == null){
//            findNavController().navigate(R.id.action_adminFragment_to_navigation_add)
//        }else{
//            Toast.makeText(context, "Already logged in", Toast.LENGTH_LONG).show()
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val aorder = view.findViewById<ImageButton>(R.id.adminorder_button)
        aorder.setOnClickListener {
            findNavController().navigate(R.id.action_adminFragment_to_adminOrderFragment)
        }
    }
}