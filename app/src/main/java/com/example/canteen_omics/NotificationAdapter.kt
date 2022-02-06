package com.example.canteen_omics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.canteen_omics.ui.notifications.Notification
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.notificationitem.view.*


class NotificationAdapter(private var notificationList: ArrayList<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.notificationitem, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = notificationList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notif = notificationList[position]
        holder.bind(notif)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                val snack: String = "Item Postion clciked: $adapterPosition"
                Snackbar.make(itemView, snack, Snackbar.LENGTH_SHORT).show()
            }
        }

        fun bind(notif: Notification) {
            itemView.notif_image.setImageResource(notif.image)
            itemView.notif_tV1.text = notif.text1.toString()
            itemView.notif_tV2.text = notif.text2.toString()

        }
    }
}