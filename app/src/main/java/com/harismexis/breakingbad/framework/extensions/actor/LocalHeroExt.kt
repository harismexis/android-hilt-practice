package com.harismexis.breakingbad.framework.extensions.actor

import com.harismexis.breakingbad.datamodel.domain.Hero
import com.harismexis.breakingbad.framework.datasource.database.table.LocalHero

fun List<LocalHero?>?.toItems(): List<Hero> {
    val items = mutableListOf<Hero>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun LocalHero.toItem(): Hero {
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

fun List<Hero?>?.toLocalItems(): List<LocalHero> {
    val localItems = mutableListOf<LocalHero>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun Hero.toLocalItem(): LocalHero {
    return LocalHero(
        this.id,
        this.name,
        this.status,
        this.species,
        this.type,
        this.gender,
        this.image
    )
}