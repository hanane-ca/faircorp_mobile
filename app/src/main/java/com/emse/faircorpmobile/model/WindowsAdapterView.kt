package com.emse.faircorpmobile.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emse.faircorpmobile.OnWindowSelectedListener
import com.emse.faircorpmobile.R

class WindowsAdapterView (val listener: OnWindowSelectedListener): RecyclerView.Adapter<WindowsAdapterView.WindowViewHolder>() {
    inner class WindowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_window_name2)
        val room: TextView = view.findViewById(R.id.txt_window_room)
        val status: TextView = view.findViewById(R.id.txt_status)
    }

    private val items = mutableListOf<WindowDto>()

    fun update(windows: List<WindowDto>) {
        items.clear()
        items.addAll(windows)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_windows_item, parent, false)
        return WindowViewHolder(view)
    }

    override fun onBindViewHolder(holder: WindowViewHolder, position: Int) {
        val window = items[position]
        holder.apply {
            name.text = window.name
            status.text = window.windowStatus.toString()
            room.text = window.roomName
            itemView.setOnClickListener { listener.onWindowSelected(window.id) } // (1)
        }
    }

    override fun onViewRecycled(holder: WindowViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}