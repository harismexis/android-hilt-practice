package com.harismexis.hiltproject.framework.util.extensions

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.hiltproject.presentation.widget.RecyclerDivider

fun RecyclerView.setDivider(
    @DrawableRes divider: Int
){
    ContextCompat.getDrawable(this.context, divider)?.let {
        this.addItemDecoration(RecyclerDivider(it))
    }
}