package com.example.canteen_omics

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

const val TAG = "FIRESTORE"

class AddMenu : Fragment() {

    private lateinit var auth: FirebaseAuth

    private lateinit var txtfsMenu: EditText
    private lateinit var txtfsDescript: EditText
    private lateinit var txtfsPrice: EditText

    private lateinit var btnfsAdd: Button
    private lateinit var btfsRetrieveData: Button
    private lateinit var btnfsLogout: Button

//    var radioGrp1: RadioGroup? = null
//    var radioGrp2: RadioGroup? = null
//    var radioGrp3: RadioGroup? = null
//    lateinit var radioBtn1: RadioButton
//    lateinit var radioBtn2: RadioButton
//    lateinit var radioBtn3: RadioButton

    //private lateinit var fsmsg1: TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            findNavController().navigate(R.id.action_addMenu_to_navigation_add)
        }else{
            Toast.makeText(activity, "Already logged in", Toast.LENGTH_LONG).show()
        }

        txtfsMenu = view.findViewById(R.id.add_menu)
        txtfsDescript = view.findViewById(R.id.add_descript)
        txtfsPrice = view.findViewById(R.id.add_price)

        btnfsAdd = view.findViewById(R.id.add_btn)
//        btfsRetrieveData = view.findViewById(R.id.fsRetrieveData)
//        btnfsLogout = view.findViewById(R.id.fsLogout)

        btnfsAdd.setOnClickListener {
            if (txtfsMenu.text.toString().trim().length>0) {
                addMenu()
            } else {
                Toast.makeText(activity, "Menu Name Needed", Toast.LENGTH_SHORT).show()
            }
        }
//        btfsRetrieveData.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            val intent = Intent(this, FirestoreRetrieveActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//        btnfsLogout.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_menu, container, false)
    }
//
//        txtfsName = findViewById(R.id.fsname)
//        txtfsMessage = findViewById(R.id.fsmessage)
//        txtfsPayment = findViewById(R.id.fsPaymentDetail)
//
//        btnfsSendMessage = findViewById(R.id.fsSendMessage)
//        btfsRetrieveData = findViewById(R.id.fsRetrieveData)
//        btnfsLogout = findViewById(R.id.fsLogout)
//
//        btnfsSendMessage.setOnClickListener {
//            if (txtfsName.text.toString().trim().length>0) {
//                uploadData()
//            } else {
//                Toast.makeText(this, "NO order submitted! Try again!", Toast.LENGTH_SHORT).show()
//            }
//        }
//        btfsRetrieveData.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            val intent = Intent(this, FirestoreRetrieveActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//        btnfsLogout.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

    private fun addMenu() {
        // @ServerTimestamp
        val calendar: Calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss , EEE")
        val dateTime = simpleDateFormat.format(calendar.time)

        val db = Firebase.firestore
        val menu = db
            .collection("menu").document("ulam")
            .collection("addulam")


        val datadoc = hashMapOf(
            "menuName" to txtfsMenu.text.toString(),
            "descript" to txtfsDescript.text.toString(),
            "price" to txtfsPrice.text.toString(),
        )
        menu.document(dateTime).set(datadoc)

        Toast.makeText(
            activity,
            "Successfully Added",
            Toast.LENGTH_SHORT
        ).show()

        txtfsMenu.setText(null)
        txtfsDescript.setText(null)
        txtfsPrice.setText(null)
    }

//    private fun addDrink() {
//        // @ServerTimestamp
//        val calendar: Calendar = Calendar.getInstance()
//        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss , EEE")
//        val dateTime = simpleDateFormat.format(calendar.time)
//
//        val db = Firebase.firestore
//        val menu = db
//            .collection("menu").document("drinks")
//            .collection("addDrink")
//
//
//        val datadoc = hashMapOf(
//            "drinkName" to txtfsMenu.text.toString(),
//            "drinkDescript" to txtfsDescript.text.toString(),
//            "drinkPrice" to txtfsPrice.text.toString(),
//        )
//        menu.document(dateTime).set(datadoc)
//
//        Toast.makeText(
//            activity,
//            "Successfully Added",
//            Toast.LENGTH_SHORT
//        ).show()
//
//        txtfsMenu.setText(null)
//        txtfsDescript.setText(null)
//        txtfsPrice.setText(null)
//    }
//    private lateinit var viewModel: AddViewModel
//
//    override fun onActivityCreated(savedInstanceState: Bundle?){
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(requireActivity()).get(AddViewModel::class.java)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_menu, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val menu = view.findViewById<EditText>(R.id.add_menu)
//        val description = view.findViewById<EditText>(R.id.add_descript)
//        val price = view.findViewById<EditText>(R.id.add_price)
//        val add = view.findViewById<Button>(R.id.add_btn)
//
//
//        add.setOnClickListener {
//            val menuStr = menu.text.toString()
//            val descriptStr = description.text.toString()
//            val priceStr = price.text.toString()
//            when {
//                menuStr.length == 0 -> {
//                    Snackbar.make(menu, "Menu name needed", Snackbar.LENGTH_SHORT)
//                        .show()
//                }
//
//                descriptStr.length == 0 -> {
//                    Snackbar.make(description, "Description Needed", Snackbar.LENGTH_SHORT)
//                        .show()
//                }
//
//                priceStr.length == 0 -> {
//                    Snackbar.make(description, "Description Needed", Snackbar.LENGTH_SHORT)
//                        .show()
//
//                }
//
//                else -> {
//                    Snackbar.make(menu, "Added Successfully", Snackbar.LENGTH_SHORT).show()
//
//                    viewModel.Menu.value = menuStr
//                    viewModel.Descript.value = descriptStr
//                    viewModel.MenuPrice.value = priceStr
//                    findNavController().navigate(R.id.action_addMenu_to_adminFragment)
//
//                }
//            }
//        }
//
//    }
//}
//
//class AddViewModel : ViewModel() {
//    var MenuName = MutableLiveData<String>()
//    var DescriptionMenu = MutableLiveData<String>()
//    var Price = MutableLiveData<String>()
//    val Menu get() = MenuName
//    val Descript get() = DescriptionMenu
//    val MenuPrice get() = Price
}
