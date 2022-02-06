package com.example.canteen_omics

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class AdminFragment : Fragment() {


    val viewModel by activityViewModels<AddViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.Menu.observe(this.viewLifecycleOwner, Observer { z ->
            val menu = view?.findViewById<TextView>(R.id.menu1_textView)
            menu?.text = z
        })
        viewModel.Descript.observe(this.viewLifecycleOwner, Observer { z ->
            val descript = view?.findViewById<TextView>(R.id.menu1_description)
            descript?.text = z
        })
        viewModel.MenuPrice.observe(this.viewLifecycleOwner, Observer { z ->
            val price = view?.findViewById<TextView>(R.id.menu1_price)
            price?.text = z
        })

        val add_button = view?.findViewById<FloatingActionButton>(R.id.fab_add)
        add_button?.setOnClickListener {
            findNavController().navigate(R.id.action_adminFragment_to_addMenu)
        }
        val aorder = view?.findViewById<ImageButton>(R.id.adminorder_button)
        aorder?.setOnClickListener {
            findNavController().navigate(R.id.action_adminFragment_to_adminOrderFragment)
        }
    }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setHasOptionsMenu(true)
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_admin, container, false)
        }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val add_button = view.findViewById<FloatingActionButton>(R.id.add_btn)
//        add_button.setOnClickListener {
//            findNavController().navigate(R.id.action_adminFragment_to_addMenu)
//        }
//        val aorder = view.findViewById<ImageButton>(R.id.adminorder_button)
//        aorder.setOnClickListener {
//            findNavController().navigate(R.id.action_adminFragment_to_adminOrderFragment)
//        }
//    }

        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.menu_main, menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId == R.id.action_logout) {
                FirebaseAuth.getInstance().signOut()
                findNavController().navigate(R.id.action_adminFragment_to_navigation_add)
                return true
            }
            return false
        }
    }