package com.example.canteen_omics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.add_drinkitem.view.*

class DrinkAdapter(options: FirestoreRecyclerOptions<DrinkModel>) :
    FirestoreRecyclerAdapter<DrinkModel, DrinkAdapter.DrinkAdapterVH>(options) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkAdapterVH {
        return DrinkAdapterVH(LayoutInflater.from(parent.context).inflate(R.layout.add_drinkitem,parent,false))
    }


    override fun onBindViewHolder(holder: DrinkAdapterVH, position: Int, model: DrinkModel) {
        holder.Drink.text = model.drinkName
        holder.drinkDescription.text = model.drinkDescript
        holder.drinkPrice.text = model.drinkPrice
    }

    class DrinkAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Drink = itemView.DrinkName
        var drinkDescription = itemView.DrinkDescript
        var drinkPrice = itemView.Drinkprice
    }
}