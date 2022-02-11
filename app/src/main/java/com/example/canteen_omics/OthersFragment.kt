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
import kotlinx.android.synthetic.main.fragment_drinks.*
import kotlinx.android.synthetic.main.fragment_drinks.recyclerView3
import kotlinx.android.synthetic.main.fragment_others.*


class OthersFragment : Fragment() {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance();
    private val collectionReference: CollectionReference = db.collection("menu").document("other")
        .collection("addOthers")

    var addOthersAdapter: OthersAdapter? = null;

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

        setUpRecyclerView()
//
//        val C3 = view.findViewById<Button>(R.id.canteen3_button)
//        C3.setOnClickListener {
//            findNavController().navigate(R.id.action_navigation_home_to_othersFragment)
//        }
    }

    fun setUpRecyclerView() {

        val query: Query = collectionReference
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<OthersModel> =
            FirestoreRecyclerOptions
                .Builder<OthersModel>().setQuery(query, OthersModel::class.java).build()

        addOthersAdapter = OthersAdapter(firestoreRecyclerOptions)

        recyclerView4.layoutManager = LinearLayoutManager(activity)
        recyclerView4.adapter = addOthersAdapter
    }

    override fun onStart() {
        super.onStart()
        addOthersAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        addOthersAdapter!!.stopListening()
    }
}