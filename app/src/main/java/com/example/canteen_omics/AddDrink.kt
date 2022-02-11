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


class AddDrink : Fragment() {

    private lateinit var auth: FirebaseAuth

    private lateinit var Drink: EditText
    private lateinit var DrinkDescript: EditText
    private lateinit var DrinkPrice: EditText

    private lateinit var Add: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_drink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Drink = view.findViewById(R.id.add_drink)
        DrinkDescript = view.findViewById(R.id.add_drinkdescript)
        DrinkPrice = view.findViewById(R.id.add_drinkprice)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            findNavController().navigate(R.id.action_addDrink_to_navigation_add2)
        }else{
            Toast.makeText(activity, "Already logged in", Toast.LENGTH_LONG).show()
        }

        Add = view.findViewById(R.id.adddrink_btn)
//        btfsRetrieveData = view.findViewById(R.id.fsRetrieveData)
//        btnfsLogout = view.findViewById(R.id.fsLogout)

        Add.setOnClickListener {
            if (Drink.text.toString().trim().length > 0) {
                addDrink()
            } else {
                Toast.makeText(activity, "Drink Name Needed", Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun addDrink() {
        // @ServerTimestamp

        val calendar: Calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss , EEE")
        val dateTime = simpleDateFormat.format(calendar.time)

        val db = Firebase.firestore
        val menu = db
            .collection("menu").document("drinks")
            .collection("addDrink")


        val datadoc = hashMapOf(
            "drinkName" to Drink.text.toString(),
            "drinkDescript" to DrinkDescript.text.toString(),
            "drinkPrice" to DrinkPrice.text.toString(),
        )
        menu.document(dateTime).set(datadoc)

        Toast.makeText(
            activity,
            "Successfully Added",
            Toast.LENGTH_SHORT
        ).show()

        Drink.setText(null)
        DrinkDescript.setText(null)
        DrinkPrice.setText(null)
    }
}