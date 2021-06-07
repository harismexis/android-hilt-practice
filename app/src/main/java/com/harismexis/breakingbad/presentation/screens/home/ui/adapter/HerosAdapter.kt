package com.harismexis.breakingbad.presentation.screens.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.databinding.VhActorItemBinding
import com.harismexis.breakingbad.datamodel.domain.Hero
import com.harismexis.breakingbad.presentation.screens.home.ui.viewholder.HeroViewHolder

class HerosAdapter(
    private val models: List<Hero>,
    private val clickListener: HeroViewHolder.HeroClickListener
) : RecyclerView.Adapter<HeroViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeroViewHolder {
        return HeroViewHolder(
            VhActorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    override fun onBindViewHolder(
        viewHolder: HeroViewHolder,
        position: Int
    ) {
        viewHolder.bind(models[position], position)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onViewDetachedFromWindow(holder: HeroViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}