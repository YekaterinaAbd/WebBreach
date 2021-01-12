package com.example.webbreach.view

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.webbreach.R
import com.example.webbreach.model.Breach
import com.example.webbreach.utils.DateUtil
import com.example.webbreach.utils.ITEM
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance(data: Bundle? = null) = DetailsFragment().apply {
            arguments = data
        }
    }

    private var item: Breach? = null

    private lateinit var toolbarName: TextView
    private lateinit var logo: ImageView
    private lateinit var title: TextView
    private lateinit var domain: TextView
    private lateinit var description: TextView
    private lateinit var dataClasses: ChipGroup
    private lateinit var breachDate: TextView
    private lateinit var addedDate: TextView
    private lateinit var modifiedDate: TextView

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        if (args?.get(ITEM) != null) {
            item = args.get(ITEM) as Breach
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        item?.let { bindData(it) }
    }

    private fun bindViews(view: View) = with(view) {
        toolbarName = findViewById(R.id.toolbar_name)
        logo = findViewById(R.id.logo)
        title = findViewById(R.id.title)
        domain = findViewById(R.id.domain)
        description = findViewById(R.id.description)
        dataClasses = findViewById(R.id.data_classes)
        breachDate = findViewById(R.id.breach_date)
        addedDate = findViewById(R.id.added_date)
        modifiedDate = findViewById(R.id.modified_date)

        findViewById<ImageView>(R.id.backButton).setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun bindData(item: Breach) {
        toolbarName.text = item.name
        title.text = item.title
        Glide.with(this).load(item.logoPath).into(logo)
        domain.text = item.domain
        description.text = Html.fromHtml(item.description)

        breachDate.text =
            getString(R.string.breach_date, DateUtil.convertIsoToDate(item.breachDate))
        addedDate.text = getString(R.string.added, DateUtil.convertIsoToDate(item.breachDate))
        modifiedDate.text =
            getString(R.string.modified, DateUtil.convertIsoToDate(item.modifiedDate))

        if (!item.dataClasses.isNullOrEmpty()) {
            for (dataClass in item.dataClasses) {
                createChip(dataClass, dataClasses)
            }
        }
    }

    private fun createChip(text: String, chipGroup: ChipGroup) {
        val chip = LayoutInflater.from(context)
            .inflate(R.layout.chip_item, chipGroup, false) as Chip
        chip.text = text
        chipGroup.addView(chip)
    }
}

