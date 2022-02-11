package com.example.canteen_omics

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_admin.*
import kotlinx.android.synthetic.main.fragment_notifications.*


class AdminFragment : Fragment() {


    private lateinit var auth: FirebaseAuth

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_open_anim)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_close_anim)}
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.from_bottom_anim)}
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.to_bottom_anim)}
    private var clicked = false

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance();
    private val collectionReference: CollectionReference = db.collection("menu").
    document("ulam").collection("addulam")

    var AdminAdapter: AdminAdapter? = null;

//    private lateinit var txtfsmsg1: TextView
//    private lateinit var txtfsmsg2: TextView
//    private lateinit var txtfsmsg3: TextView
//    private lateinit var txtfsmsg4: TextView
//    private lateinit var txtfsmsg5: TextView
//    private lateinit var txtfsmsg6: TextView
//    private lateinit var txtfsmsg7: TextView
//    private lateinit var txtfsmsg8: TextView
//
//    private lateinit var txtDetailed: TextView
//
//
//    private lateinit var btRetrieveBack: Button

    //recyclersViewCode
    // private lateinit var rView: RecyclerView

//    private val db: FirebaseFirestore = FirebaseFirestore.getInstance();
//    private val collectionReference: CollectionReference = db.collection("menu").document("ulam")
//    .collection("addulam")
//
//    var addorderAdapter: AddOrderAdapter? = null;
//
//
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
//            findNavController().navigate(R.id.action_adminFragment_to_navigation_add)
        }else{
            Toast.makeText(context, "Already logged in", Toast.LENGTH_LONG).show()
        }

        fab_add.setOnClickListener{
            onAddButtonClicked()
        }

        fab_addmenu.setOnClickListener{
            findNavController().navigate(R.id.action_adminFragment_to_addMenu)
        }

        fab_adddrink.setOnClickListener{
            findNavController().navigate(R.id.action_adminFragment_to_addDrink)
        }

        fab_addothers.setOnClickListener{
            findNavController().navigate(R.id.action_adminFragment_to_addOthers)
        }

        setUpRecyclerView()

    }

        fun setUpRecyclerView() {

        val query: Query = collectionReference
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<AdminModel> =
            FirestoreRecyclerOptions
                .Builder<AdminModel>().setQuery(query, AdminModel::class.java).build()

        AdminAdapter = AdminAdapter(firestoreRecyclerOptions)

        recyclerViewadmin.layoutManager = LinearLayoutManager(activity)
        recyclerViewadmin.adapter = AdminAdapter
    }

    override fun onStart() {
        super.onStart()
        AdminAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        AdminAdapter!!.stopListening()
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked:Boolean) {
        if(!clicked){
            fab_addmenu.visibility = View.VISIBLE
            fab_adddrink.visibility = View.VISIBLE
            fab_addothers.visibility = View.VISIBLE
        }else{
            fab_addmenu.visibility = View.INVISIBLE
            fab_adddrink.visibility = View.INVISIBLE
            fab_addothers.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if(!clicked){
            fab_addmenu.startAnimation(fromBottom)
            fab_adddrink.startAnimation(fromBottom)
            fab_addothers.startAnimation(fromBottom)
            fab_add.startAnimation(rotateOpen)
        }else{
            fab_addmenu.startAnimation(toBottom)
            fab_adddrink.startAnimation(toBottom)
            fab_addothers.startAnimation(toBottom)
            fab_add.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked: Boolean){
        if(!clicked){
            fab_addmenu.isClickable = true
            fab_adddrink.isClickable = true
            fab_addothers.isClickable = true
        }else{
            fab_addmenu.isClickable = false
            fab_adddrink.isClickable = false
            fab_addothers.isClickable = false
        }
    }


//        val aorder = view.findViewById<ImageButton>(R.id.adminorder_button)
//        aorder.setOnClickListener {
//            findNavController().navigate(R.id.action_adminFragment_to_adminOrderFragment)
//        }
//
//        setUpRecyclerView()
//    }
//
//    fun setUpRecyclerView() {
//
//        val query: Query = collectionReference
//        val firestoreRecyclerOptions: FirestoreRecyclerOptions<AddOrderModel> =
//            FirestoreRecyclerOptions
//                .Builder<AddOrderModel>().setQuery(query, AddOrderModel::class.java).build()
//
//        addorderAdapter = AddOrderAdapter(firestoreRecyclerOptions)
//
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        recyclerView.adapter = addorderAdapter
//    }
//
//    override fun onStart() {
//        super.onStart()
//        addorderAdapter!!.startListening()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        addorderAdapter!!.stopListening()
//    }
//
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
