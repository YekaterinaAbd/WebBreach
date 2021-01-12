package com.example.webbreach.view

import androidx.recyclerview.widget.DiffUtil
import com.example.webbreach.model.Breach

class DiffUtilCallback : DiffUtil.ItemCallback<Breach>() {

    override fun areItemsTheSame(oldItem: Breach, newItem: Breach): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Breach, newItem: Breach): Boolean {
        return oldItem == newItem
    }
}