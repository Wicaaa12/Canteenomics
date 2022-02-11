package com.example.canteen_omics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_canteen_1.*
import kotlinx.android.synthetic.main.fragment_canteen_1.recyclerView2
import kotlinx.android.synthetic.main.fragment_drinks.*


class DrinksFragment : Fragment() {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance();
    private val collectionReference: CollectionReference = db.collection("menu").document("drinks")
        .collection("addDrink")

    var addDrinkAdapter: DrinkAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drinks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menu = view.findViewById<Button>(R.id.dmenu_button)
        menu.setOnClickListener {
            findNavController().navigate(R.id.action_drinksFragment_to_canteen_1Fragment)
        }

        val dothers = view.findViewById<Button>(R.id.dothers_button)
        dothers.setOnClickListener {
            findNavController().navigate(R.id.action_drinksFragment_to_othersFragment)
        }

        setUpRecyclerView()
//
//        val C3 = view.findViewById<Button>(R.id.canteen3_button)
//        C3.setOnClickListener {
//            findNavController().navigate(R.id.action_navigation_home_to_othersFragment)
//        }
    }

    fun setUpRecyclerView() {

        val query: Query = collectionReference
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<DrinkModel> =
            FirestoreRecyclerOptions
                .Builder<DrinkModel>().setQuery(query, DrinkModel::class.java).build()

        addDrinkAdapter = DrinkAdapter(firestoreRecyclerOptions)

        recyclerView3.layoutManager = LinearLayoutManager(activity)
        recyclerView3.adapter = addDrinkAdapter
    }

    override fun onStart() {
        super.onStart()
        addDrinkAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        addDrinkAdapter!!.stopListening()
    }

}