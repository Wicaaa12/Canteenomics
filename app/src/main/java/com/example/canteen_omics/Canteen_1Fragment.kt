package com.example.canteen_omics

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_canteen_1.*


class Canteen_1Fragment : Fragment() {


    private val db: FirebaseFirestore = FirebaseFirestore.getInstance();
    private val collectionReference: CollectionReference = db.collection("menu").document("ulam")
        .collection("addulam")

    var addorderAdapter: AddOrderAdapter? = null;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_canteen_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val add_button = view.findViewById<FloatingActionButton>(R.id.fab_add)
//        add_button.setOnClickListener {
//            findNavController().navigate(R.id.action_adminFragment_to_addMenu)
//        }
//        val aorder = view.findViewById<ImageButton>(R.id.adminorder_button)
//        aorder.setOnClickListener {
//            findNavController().navigate(R.id.action_adminFragment_to_adminOrderFragment)
//        }

        val drinks = view.findViewById<Button>(R.id.drinks_button)
        drinks.setOnClickListener {
            findNavController().navigate(R.id.action_canteen_1Fragment_to_drinksFragment)
        }

        val others = view.findViewById<Button>(R.id.other_button)
        others.setOnClickListener {
            findNavController().navigate(R.id.action_canteen_1Fragment_to_othersFragment)
        }

        setUpRecyclerView()
    }

    fun setUpRecyclerView() {

        val query: Query = collectionReference
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<AddOrderModel> =
            FirestoreRecyclerOptions
                .Builder<AddOrderModel>().setQuery(query, AddOrderModel::class.java).build()

        addorderAdapter = AddOrderAdapter(firestoreRecyclerOptions)

        recyclerView2.layoutManager = LinearLayoutManager(activity)
        recyclerView2.adapter = addorderAdapter
    }

    override fun onStart() {
        super.onStart()
        addorderAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        addorderAdapter!!.stopListening()
    }

//    class AddViewModel : ViewModel() {
//    var MenuName = MutableLiveData<String>()
//    var DescriptionMenu = MutableLiveData<String>()
//    var Price = MutableLiveData<String>()
//    val Menu get() = MenuName
//    val Descript get() = DescriptionMenu
//    val MenuPrice get() = Price
//    }
}