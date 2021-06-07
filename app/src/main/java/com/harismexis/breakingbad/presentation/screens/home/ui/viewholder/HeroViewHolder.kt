package com.harismexis.breakingbad.presentation.screens.home.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.databinding.VhActorItemBinding
import com.harismexis.breakingbad.datamodel.domain.Hero
import com.harismexis.breakingbad.framework.extensions.populateWithGlide

class HeroViewHolder(
    private val binding: VhActorItemBinding,
    private val itemClick: ActorClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface ActorClickListener {
        fun onActorClick(item: Hero, position: Int)
    }

    fun bind(
        item: Hero,
        position: Int
    ) {
        itemView.context.populateWithGlide(binding.imgView, item.image)
        binding.txtName.text = item.name
        binding.txtMeta.text = item.species
        itemView.setOnClickListener {
            itemClick.onActorClick(item, position)
        }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}