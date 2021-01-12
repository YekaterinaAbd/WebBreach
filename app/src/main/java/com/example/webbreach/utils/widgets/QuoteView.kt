package com.example.webbreach.utils.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.webbreach.R

class QuoteView : LinearLayout {

    private lateinit var quoteText: TextView
    private lateinit var quoteAuthor: TextView

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    fun setQuote(quote: String?, author: String?) {
        if (quote == null) this.visibility = View.GONE
        quoteText.text = quote
        quoteAuthor.text = author
    }

    private fun init(context: Context) {
        inflate(context, R.layout.quote_view, this)

        quoteText = findViewById(R.id.quote_text)
        quoteAuthor = findViewById(R.id.quote_author)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        init(context)
    }
}