package com.example.webbreach.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webbreach.R
import com.example.webbreach.model.SiteBreach
import com.example.webbreach.view_model.SiteBreachState

class ListFragment : Fragment(R.layout.fragment_list) {

    companion object {
        private const val TAG = "list_fragment"
    }

    private lateinit var recyclerView: RecyclerView

    private val adapter: BreachAdapter by lazy {
        BreachAdapter(::onClick)
    }

    private val viewModel: SiteBreachViewHolder by viewModels()

//    private val viewModel: SiteBreachViewModel by lazy {
//        SiteBreachViewModel(SiteBreachRepositoryImpl(RetrofitClient.getBreachApi()))
//    }

    private fun onClick(item: SiteBreach) {
        parentFragmentManager.beginTransaction().apply {
            add(R.id.frame, DetailsFragment.newInstance(bundleOf(ITEM to item)), DETAILS_FRAGMENT)
            addToBackStack(LIST_FRAGMENT)
            commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setAdapter()
        setData()
    }

    private fun bindViews(view: View) = with(view) {
        recyclerView = findViewById(R.id.recycler_view)
    }

    private fun setAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    private fun setData() {
        viewModel.liveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is SiteBreachState.SiteBreachesResult -> {
                    adapter.submitList(result.siteBreaches.take(50))
                }
                is SiteBreachState.Error -> {
                    Log.d(TAG, result.message)
                }
            }
        })
    }

    override fun onDestroyView() {
        recyclerView.adapter = null
        super.onDestroyView()
    }
}