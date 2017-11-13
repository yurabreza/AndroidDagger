package com.androiddagger.presentation.utils

import android.view.View

/**
 * Prevents double tap to avoid double trigger actions, which can lead to crash
 * */
fun View.disableAfterClick() {
    isEnabled = false
    postDelayed({ isEnabled = true }, 300)
}