package com.example.webbreach.view

import androidx.recyclerview.widget.DiffUtil
import com.example.webbreach.model.SiteBreach

class DiffUtilCallback : DiffUtil.ItemCallback<SiteBreach>() {
    override fun areItemsTheSame(oldItem: SiteBreach, newItem: SiteBreach): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: SiteBreach, newItem: SiteBreach): Boolean {
        return oldItem == newItem
    }
}