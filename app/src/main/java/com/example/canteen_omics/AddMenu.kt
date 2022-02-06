package com.example.canteen_omics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar


class AddMenu : Fragment() {

    private lateinit var viewModel: AddViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AddViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menu = view.findViewById<EditText>(R.id.add_menu)
        val description = view.findViewById<EditText>(R.id.add_descript)
        val price = view.findViewById<EditText>(R.id.add_price)
        val add = view.findViewById<Button>(R.id.add_btn)
        add.setOnClickListener {
            val menuStr = menu.text.toString()
            val descriptStr = description.text.toString()
            val priceStr = price.text.toString()
            when {
                menuStr.length == 0 -> {
                    Snackbar.make(menu, "Menu name needed", Snackbar.LENGTH_SHORT)
                        .show()
                }

                descriptStr.length == 0 -> {
                    Snackbar.make(description, "Description Needed", Snackbar.LENGTH_SHORT)
                        .show()
                }

                priceStr.length == 0 -> {
                    Snackbar.make(description, "Description Needed", Snackbar.LENGTH_SHORT)
                        .show()

                }

                else -> {
                    Snackbar.make(menu, "Added Successfully", Snackbar.LENGTH_SHORT).show()

                    viewModel.Menu.value = menuStr
                    viewModel.Descript.value = descriptStr
                    viewModel.MenuPrice.value = priceStr
                    findNavController().navigate(R.id.action_addMenu_to_adminFragment)

                }
            }
        }

    }
}

class AddViewModel : ViewModel() {
    var MenuName = MutableLiveData<String>()
    var DescriptionMenu = MutableLiveData<String>()
    var Price = MutableLiveData<String>()
    val Menu get() = MenuName
    val Descript get() = DescriptionMenu
    val MenuPrice get() = Price
}
