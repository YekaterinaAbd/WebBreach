package com.example.webbreach.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.io.Serializable

inline fun <reified T : Activity> Context.intentFor(vararg args: Pair<String, Any?>): Intent {
    val intent = Intent(this, T::class.java)
    for ((key, value) in args) {
        when (value) {
            is String -> intent.putExtra(key, value)
            is Int -> intent.putExtra(key, value)
            is Serializable -> intent.putExtra(key, value)
        }
    }
    return intent
}