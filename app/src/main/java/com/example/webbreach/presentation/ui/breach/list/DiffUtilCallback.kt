package com.example.webbreach.presentation.ui.breach.list

import androidx.recyclerview.widget.DiffUtil
import com.example.webbreach.presentation.model.BreachModel

class DiffUtilCallback : DiffUtil.ItemCallback<BreachModel>() {

    override fun areItemsTheSame(oldItem: BreachModel, newItem: BreachModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: BreachModel, newItem: BreachModel): Boolean {
        return oldItem == newItem
    }
}