package com.example.webbreach.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.webbreach.R
import com.example.webbreach.model.SiteBreach

class BreachAdapter(
    private val onClick: (item: SiteBreach) -> Unit
) : ListAdapter<SiteBreach, SiteBreachViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteBreachViewHolder {
        return SiteBreachViewHolder(
            onClick,
            LayoutInflater.from(parent.context).inflate(R.layout.breach_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SiteBreachViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
