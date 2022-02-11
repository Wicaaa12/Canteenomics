package com.example.canteen_omics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*


class AddOthers : Fragment() {

    private lateinit var auth: FirebaseAuth

    private lateinit var Others: EditText
    private lateinit var othersDescript: EditText
    private lateinit var othersPrice: EditText

    private lateinit var Add: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_others, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            findNavController().navigate(R.id.action_addMenu_to_navigation_add)
        }else{
            Toast.makeText(activity, "Already logged in", Toast.LENGTH_LONG).show()
        }

        Others = view.findViewById(R.id.add_others)
        othersDescript = view.findViewById(R.id.add_otherdescript)
        othersPrice = view.findViewById(R.id.add_othersprice)

        Add = view.findViewById(R.id.addothers_btn)
//        btfsRetrieveData = view.findViewById(R.id.fsRetrieveData)
//        btnfsLogout = view.findViewById(R.id.fsLogout)

        Add.setOnClickListener {
            if (Others.text.toString().trim().length > 0) {
                addOthers()
            } else {
                Toast.makeText(activity, "Others Name Needed", Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun addOthers() {
        // @ServerTimestamp
        val calendar: Calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss , EEE")
        val dateTime = simpleDateFormat.format(calendar.time)

        val db = Firebase.firestore
        val menu = db
            .collection("menu").document("other")
            .collection("addOthers")


        val datadoc = hashMapOf(
            "otherName" to Others.text.toString(),
            "otherDescript" to othersDescript.text.toString(),
            "otherPrice" to othersPrice.text.toString(),
        )
        menu.document(dateTime).set(datadoc)

        Toast.makeText(
            activity,
            "Successfully Added",
            Toast.LENGTH_SHORT
        ).show()

        Others.setText(null)
        othersDescript.setText(null)
        othersPrice.setText(null)
    }
}