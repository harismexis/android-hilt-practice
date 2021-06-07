package com.harismexis.breakingbad.framework.extensions.actor

import com.harismexis.breakingbad.datamodel.domain.Actor
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteActor
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteCharacters

fun RemoteCharacters?.toItems(): List<Actor> {
    val items = mutableListOf<Actor>()
    if (this == null) return items.toList()
    if(this.results.isNullOrEmpty()) return items.toList()
    val filteredList = this.results.filter { it.id != null }
    items.addAll(filteredList.map {
        it !!.toItem(it.id !!)
    })
    return items.toList()
}

private fun RemoteActor.toItem(id: Int): Actor {
    return Actor(
        id,
        this.name,
        this.status,
        this.species,
        this.type,
        this.gender,
        this.image
    )
}
