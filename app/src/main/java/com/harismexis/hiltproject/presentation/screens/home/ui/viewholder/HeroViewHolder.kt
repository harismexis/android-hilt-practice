package com.harismexis.hiltproject.presentation.screens.home.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.hiltproject.databinding.VhHeroItemBinding
import com.harismexis.hiltproject.datamodel.domain.Hero
import com.harismexis.hiltproject.framework.extensions.populateWithGlide

class HeroViewHolder(
    private val binding: VhHeroItemBinding,
    private val itemClick: HeroClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface HeroClickListener {
        fun onHeroClick(item: Hero, position: Int)
    }

    fun bind(
        item: Hero,
        position: Int
    ) {
        itemView.context.populateWithGlide(binding.imgView, item.image)
        binding.txtName.text = item.name
        binding.txtMeta.text = item.species
        itemView.setOnClickListener {
            itemClick.onHeroClick(item, position)
        }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}