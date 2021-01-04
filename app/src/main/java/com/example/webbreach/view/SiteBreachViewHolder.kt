package com.example.webbreach.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webbreach.R
import com.example.webbreach.model.SiteBreach

class SiteBreachViewHolder(
    private val onClick: (item: SiteBreach) -> Unit,
    view: View
) : RecyclerView.ViewHolder(view) {

    private val logo: ImageView = view.findViewById(R.id.logo)
    private val name: TextView = view.findViewById(R.id.name)
    private val domain: TextView = view.findViewById(R.id.domain)
    private val breachDate: TextView = view.findViewById(R.id.breach_date)
    private val pwnCount: TextView = view.findViewById(R.id.pwn_count)

    fun bind(item: SiteBreach?) {
        if (item == null) return

        Glide.with(itemView.context).load(item.logoPath).into(logo)

        name.text = item.name
        domain.text = item.domain
        breachDate.text = item.breachDate
        pwnCount.text = itemView.context.getString(R.string.accounts_number, item.pwnCount)

        itemView.setOnClickListener {
            onClick.invoke(item)
        }
    }
}