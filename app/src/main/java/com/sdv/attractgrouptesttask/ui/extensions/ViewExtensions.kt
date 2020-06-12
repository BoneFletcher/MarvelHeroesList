package com.sdv.attractgrouptesttask.ui.extensions

import android.view.View

/**
 * Created by FrostEagle on 04.05.2020.
 * My Email: denisshakhov21@gmail.com
 * Skype: lifeforlight
 */

    fun View.visibleOrGone(visible: Boolean) {
        visibility = if(visible) View.VISIBLE else View.GONE
    }
