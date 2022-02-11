package com.example.canteen_omics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.admin_item.view.*
import kotlinx.android.synthetic.main.admin_itemm.view.*

class AdminAdapter(options: FirestoreRecyclerOptions<AdminModel>) :
    FirestoreRecyclerAdapter<AdminModel, AdminAdapter.AdminAdapterVH>(options) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminAdapterVH {
        return AdminAdapterVH(LayoutInflater.from(parent.context).inflate(R.layout.admin_itemm,parent, false))
    }


    override fun onBindViewHolder(holder: AdminAdapterVH, position: Int, model: AdminModel) {
        holder.Name.text = model.menuName
        holder.Description.text = model.descript
        holder.Price.text = model.price

    }

    class AdminAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Name = itemView.Name
        var Description = itemView.Description
        var Price = itemView.Pricee

    }

}