package com.harismexis.hiltproject.framework.extensions

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.hiltproject.framework.divider.RecyclerDivider

fun RecyclerView.setDivider(
    @DrawableRes divider: Int
){
    ContextCompat.getDrawable(this.context, divider)?.let {
        this.addItemDecoration(RecyclerDivider(it))
    }
}