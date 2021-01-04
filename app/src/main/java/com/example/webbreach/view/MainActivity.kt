package com.example.webbreach.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webbreach.R
import com.example.webbreach.model.SiteBreach
import com.example.webbreach.repository.SiteBreachRepositoryImpl
import com.example.webbreach.retrofit.RetrofitClient
import com.example.webbreach.view_model.SiteBreachState
import com.example.webbreach.view_model.SiteBreachViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private val adapter: BreachAdapter by lazy {
        BreachAdapter(::onClick)
    }

    private val viewModel: SiteBreachViewModel by lazy {
        SiteBreachViewModel(SiteBreachRepositoryImpl(RetrofitClient.getBreachApi()))
    }

    private fun onClick(item: SiteBreach) {
        val intent = intentFor<DetailsActivity>(ITEM to item)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        setData()
    }

    private fun bindViews() {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setData() {
        viewModel.liveData.observe(this, { result ->
            when (result) {
                is SiteBreachState.SiteBreachesResult -> {
                    adapter.submitList(result.siteBreaches.take(50))
                }
                is SiteBreachState.Error -> {
                    Log.d("testt", result.message)
                }
            }
        })
    }
}