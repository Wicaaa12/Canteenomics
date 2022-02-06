package com.example.canteen_omics.ui.notifications

import android.graphics.Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.canteen_omics.NotificationAdapter
import com.example.canteen_omics.R
import com.example.canteen_omics.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var notificationList = arrayListOf<Notification>(
            Notification(R.drawable.logo, " Lorem ipsum", "Lorem ipsum"),
            Notification(R.drawable.logo, " Lorem ipsum", "Lorem ipsum"),
            Notification(R.drawable.logo, " Lorem ipsum", "Lorem ipsum"),
            Notification(R.drawable.logo, " Lorem ipsum", "Lorem ipsum"),
            Notification(R.drawable.logo, " Lorem ipsum", "Lorem ipsum"),
        )
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val notificationAdapter = NotificationAdapter(notificationList)
//        recyclerView.adapter = recyclerAdapter
//        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//        recyclerView.setHasFixedSize(true)
        recyclerView.apply {
            adapter = notificationAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}