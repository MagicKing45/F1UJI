package com.example.f1uji.Selector

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.f1uji.Common.Circuit
import com.example.f1uji.Common.Driver
import com.example.f1uji.Common.location
import com.example.f1uji.R

class CircuitsRVAdapter(val circuits : List<Circuit>, val onClickListener : (Circuit)-> Unit):
    RecyclerView.Adapter<CircuitsRVAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.driverName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item__driver, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return circuits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(circuits[position]) {
            holder.name.text = circuitName + "/" + Location?.country
            holder.itemView.setOnClickListener {
                onClickListener(circuits[position])
            }
        }
    }
}