package com.example.webbreach.presentation.ui.breach.list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webbreach.R
import com.example.webbreach.presentation.model.BreachModel
import com.example.webbreach.presentation.ui.breach.BreachState
import com.example.webbreach.presentation.ui.breach.BreachViewModel
import com.example.webbreach.presentation.ui.breach.details.DetailsFragment
import com.example.webbreach.presentation.ui.quote.QuoteState
import com.example.webbreach.presentation.ui.quote.QuoteViewModel
import com.example.webbreach.presentation.utils.DETAILS_FRAGMENT
import com.example.webbreach.presentation.utils.ITEM
import com.example.webbreach.presentation.utils.LIST_FRAGMENT
import com.example.webbreach.presentation.utils.widgets.QuoteView
import org.koin.android.ext.android.inject
import timber.log.Timber

class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var quoteView: QuoteView
    private lateinit var recyclerView: RecyclerView

    private val adapter: BreachAdapter by lazy {
        BreachAdapter(::onClick)
    }

    private val breachViewModel: BreachViewModel by inject()
    private val quoteViewModel: QuoteViewModel by inject()

    private fun onClick(item: BreachModel) {
        parentFragmentManager.beginTransaction().apply {
            add(
                R.id.frame,
                DetailsFragment.newInstance(bundleOf(ITEM to item)),
                DETAILS_FRAGMENT
            )
            addToBackStack(LIST_FRAGMENT)
            commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setAdapter()
        getQuote()
        setData()
    }

    private fun bindViews(view: View) = with(view) {
        quoteView = findViewById(R.id.quote_view)
        recyclerView = findViewById(R.id.recycler_view)
    }

    private fun setAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun getQuote(){
        quoteViewModel.getQuote()
    }

    private fun setData() {
        breachViewModel.breachLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is BreachState.BreachListResult -> {
                    adapter.submitList(result.list.take(50))
                }
                is BreachState.Error -> {
                    Timber.d(result.message)
                }
            }
        }
        quoteViewModel.quoteLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is QuoteState.QuoteResult -> {
                    quoteView.setQuote(result.quote.quoteText, result.quote.quoteAuthor)
                }
                is QuoteState.Error -> {
                    Timber.d(result.message)
                }
            }
        }
    }

    override fun onDestroyView() {
        recyclerView.adapter = null
        super.onDestroyView()
    }
}