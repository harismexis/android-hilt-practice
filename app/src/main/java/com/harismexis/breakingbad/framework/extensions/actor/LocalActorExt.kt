package com.harismexis.breakingbad.framework.extensions.actor

import com.harismexis.breakingbad.datamodel.domain.Hero
import com.harismexis.breakingbad.framework.datasource.database.table.LocalActor

fun List<LocalActor?>?.toItems(): List<Hero> {
    val items = mutableListOf<Hero>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun LocalActor.toItem(): Hero {
    return Hero(
        this.id,
        this.name,
        this.status,
        this.species,
        this.type,
        this.gender,
        this.image
    )
}

fun List<Hero?>?.toLocalItems(): List<LocalActor> {
    val localItems = mutableListOf<LocalActor>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun Hero.toLocalItem(): LocalActor {
    return LocalActor(
        this.id,
        this.name,
        this.status,
        this.species,
        this.type,
        this.gender,
        this.image
    )
}
