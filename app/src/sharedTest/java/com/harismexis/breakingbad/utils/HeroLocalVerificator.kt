package com.harismexis.breakingbad.utils

import com.harismexis.breakingbad.datamodel.domain.Hero
import com.harismexis.breakingbad.framework.datasource.database.table.LocalHero
import org.junit.Assert

class HeroLocalVerificator {

    fun verifyActorsAgainstLocalActors(
        actual: List<Hero>,
        localHeroes: List<LocalHero>
    ) {
        verifyListsHaveSameSize(actual, localHeroes)
        localHeroes.forEachIndexed { index, localActor ->
            val actor = actual[index]
            verifyActorAgainstLocalActor(actor, localActor)
        }
    }

    private fun verifyActorAgainstLocalActor(
        actual: Hero,
        localHero: LocalHero
    ) {
        Assert.assertEquals(localHero.id, actual.id)
        Assert.assertEquals(localHero.name, actual.name)
        Assert.assertEquals(localHero.status, actual.status)
        Assert.assertEquals(localHero.species, actual.species)
        Assert.assertEquals(localHero.type, actual.type)
        Assert.assertEquals(localHero.gender, actual.gender)
        Assert.assertEquals(localHero.image, actual.image)
    }

    fun verifyLocalActorsAgainstActors(
        actual: List<LocalHero>,
        heroes: List<Hero>
    ) {
        verifyListsHaveSameSize(actual, heroes)
        heroes.forEachIndexed { index, item ->
            val localItem = actual[index]
            verifyLocalActorAgainstActor(localItem, item)
        }
    }

    private fun verifyLocalActorAgainstActor(
        actual: LocalHero,
        hero: Hero
    ) {
        Assert.assertEquals(hero.id, actual.id)
        Assert.assertEquals(hero.name, actual.name)
        Assert.assertEquals(hero.status, actual.status)
        Assert.assertEquals(hero.species, actual.species)
        Assert.assertEquals(hero.status, actual.status)
        Assert.assertEquals(hero.type, actual.type)
        Assert.assertEquals(hero.gender, actual.gender)
        Assert.assertEquals(hero.image, actual.image)
    }

}

