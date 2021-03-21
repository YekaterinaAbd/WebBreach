package com.example.webbreach.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.webbreach.R
import com.example.webbreach.presentation.ui.breach.list.ListFragment
import com.example.webbreach.presentation.utils.LIST_FRAGMENT

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame, ListFragment(), LIST_FRAGMENT)
            .commit()
    }
}
