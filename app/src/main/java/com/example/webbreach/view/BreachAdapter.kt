package com.example.webbreach.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webbreach.R
import com.example.webbreach.model.Breach
import com.example.webbreach.utils.DateUtil

class BreachAdapter(
    private val onClick: (item: Breach) -> Unit
) : ListAdapter<Breach, BreachViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreachViewHolder {
        return BreachViewHolder(
            onClick,
            LayoutInflater.from(parent.context).inflate(R.layout.breach_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BreachViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BreachViewHolder(
    private val onClick: (item: Breach) -> Unit,
    view: View
) : RecyclerView.ViewHolder(view) {

    private val logo: ImageView = view.findViewById(R.id.logo)
    private val name: TextView = view.findViewById(R.id.name)
    private val domain: TextView = view.findViewById(R.id.domain)
    private val breachDate: TextView = view.findViewById(R.id.breach_date)
    private val pwnCount: TextView = view.findViewById(R.id.pwn_count)

    fun bind(item: Breach?) {
        if (item == null) return

        Glide.with(itemView.context).load(item.logoPath).into(logo)

        name.text = item.name
        domain.text = item.domain

        val breachDateString = DateUtil.convertIsoToDate(item.breachDate)
        breachDate.text = itemView.context.getString(R.string.breach_date, breachDateString)
        pwnCount.text = itemView.context.getString(R.string.accounts_number, item.pwnCount)

        itemView.setOnClickListener {
            onClick.invoke(item)
        }
    }
}

