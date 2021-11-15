package com.emse.faircorpmobile.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emse.faircorpmobile.OnRoomSelectedListener
import com.emse.faircorpmobile.R

class RoomsAdapterView (val listener: OnRoomSelectedListener): RecyclerView.Adapter<RoomsAdapterView.RoomViewHolder>() {
    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_roomName)
        val currentTemp: TextView = view.findViewById(R.id.txt_current_temp)
        val targetTemp: TextView = view.findViewById(R.id.txt_target_temp)
    }
    private val items = mutableListOf<RoomDto>()

    fun update(rooms: List<RoomDto>) {
        items.clear()
        items.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsAdapterView.RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_rooms_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomsAdapterView.RoomViewHolder, position: Int) {
        val room = items[position]
        holder.apply {
            name.text = room.name
            currentTemp.text = room.currentTemp.toString()
            targetTemp.text = room.targetTemp.toString()
            itemView.setOnClickListener { listener.onRoomSelected(room.id) }
        }
    }

    override fun onViewRecycled(holder: RoomsAdapterView.RoomViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}