package com.example.canteen_omics.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.canteen_omics.R
import com.example.canteen_omics.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val C1 = view.findViewById<Button>(R.id.canteen1_button)
        C1.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_canteen_1Fragment)
        }

//        val C2 = view.findViewById<Button>(R.id.canteen2_button)
//        C2.setOnClickListener {
//            findNavController().navigate(R.id.action_navigation_home_to_drinksFragment)
//        }
//
//        val C3 = view.findViewById<Button>(R.id.canteen3_button)
//        C3.setOnClickListener {
//            findNavController().navigate(R.id.action_navigation_home_to_othersFragment)
//        }
    }
}