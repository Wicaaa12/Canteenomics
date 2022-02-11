package com.example.canteen_omics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.add_othersitem.view.*

class OthersAdapter(options: FirestoreRecyclerOptions<OthersModel>) :
    FirestoreRecyclerAdapter<OthersModel, OthersAdapter.OthersAdapterVH>(options) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OthersAdapterVH {
        return OthersAdapter.OthersAdapterVH(
            LayoutInflater.from(parent.context).inflate(R.layout.add_othersitem, parent, false)
        )
    }


    override fun onBindViewHolder(holder: OthersAdapterVH, position: Int, model: OthersModel) {
        holder.Others.text = model.otherName
        holder.othersDescription.text = model.otherDescript
        holder.othersPrice.text = model.otherPrice
    }

    class OthersAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var Others = itemView.OtherName
        var othersDescription = itemView.OtherDescript
        var othersPrice = itemView.Otherprice
    }
}