package com.example.webbreach.view

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.webbreach.R
import com.example.webbreach.model.SiteBreach
import com.example.webbreach.repository.SiteBreachRepositoryImpl
import com.example.webbreach.retrofit.RetrofitClient
import com.example.webbreach.view_model.SiteBreachDetailsState
import com.example.webbreach.view_model.SiteBreachDetailsViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class DetailsActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "details_activity"
    }

    private var item: SiteBreach? = null

    private lateinit var toolbarName: TextView
    private lateinit var logo: ImageView
    private lateinit var title: TextView
    private lateinit var domain: TextView
    private lateinit var description: TextView
    private lateinit var dataClasses: ChipGroup
    private lateinit var breachDate: TextView
    private lateinit var addedDate: TextView
    private lateinit var modifiedDate: TextView

    private val viewModel: SiteBreachDetailsViewModel by lazy {
        SiteBreachDetailsViewModel(SiteBreachRepositoryImpl(RetrofitClient.getBreachApi()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        item = intent.getSerializableExtra(ITEM) as SiteBreach
        setContentView(R.layout.activity_details)
        bindViews()
        setData()
    }

    private fun bindViews() {
        toolbarName = findViewById(R.id.toolbar_name)
        logo = findViewById(R.id.logo)
        title = findViewById(R.id.title)
        domain = findViewById(R.id.domain)
        description = findViewById(R.id.description)
        dataClasses = findViewById(R.id.data_classes)
        breachDate = findViewById(R.id.breach_date)
        addedDate = findViewById(R.id.added_date)
        modifiedDate = findViewById(R.id.modified_date)
    }

    private fun setData() {
        if (item == null) return
        item?.name?.let { viewModel.getSiteBreachDetails(it) }
        viewModel.liveData.observe(this, { result ->
            when (result) {
                is SiteBreachDetailsState.SiteBreachResult -> {
                    bindData(result.siteBreach)
                }
                is SiteBreachDetailsState.Error -> {
                    Log.d(TAG, result.message)
                }
            }
        })
    }

    private fun bindData(item: SiteBreach) {
        title.text = item.title
        toolbarName.text = item.name
        Glide.with(this).load(item.logoPath).into(logo)
        domain.text = item.domain
        description.text = Html.fromHtml(item.description)
        breachDate.text = getString(R.string.breach_date, item.breachDate)
        addedDate.text = getString(R.string.added, item.addedDate)
        modifiedDate.text = getString(R.string.modified, item.modifiedDate)
        if (!item.dataClasses.isNullOrEmpty()) {
            for (dataClass in item.dataClasses) {
                createChip(dataClass, dataClasses)
            }
        }

    }

    private fun createChip(text: String, chipGroup: ChipGroup) {
        val chip = LayoutInflater.from(this)
            .inflate(R.layout.chip_item, chipGroup, false) as Chip
        chip.text = text
        chipGroup.addView(chip)
    }
}

