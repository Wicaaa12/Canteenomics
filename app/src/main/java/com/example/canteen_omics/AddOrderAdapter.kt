package com.example.canteen_omics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.admin_item.view.*

class AddOrderAdapter(options: FirestoreRecyclerOptions<AddOrderModel>) :
    FirestoreRecyclerAdapter<AddOrderModel, AddOrderAdapter.AddOrderAdapterVH>(options) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddOrderAdapterVH {
        return AddOrderAdapterVH(LayoutInflater.from(parent.context).inflate(R.layout.admin_item,parent, false))
    }


    override fun onBindViewHolder(holder: AddOrderAdapterVH, position: Int, model: AddOrderModel) {
        holder.Menu.text = model.menuName
        holder.Description.text = model.descript
        holder.Price.text = model.price
    }

    class AddOrderAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Menu = itemView.MenuName
        var Description = itemView.Descript
        var Price = itemView.price

    }

}