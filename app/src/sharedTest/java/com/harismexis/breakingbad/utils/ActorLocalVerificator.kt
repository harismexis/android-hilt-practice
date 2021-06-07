package com.harismexis.breakingbad.utils

import com.harismexis.breakingbad.datamodel.domain.Hero
import com.harismexis.breakingbad.framework.datasource.database.table.LocalActor
import org.junit.Assert

class ActorLocalVerificator {

    fun verifyActorsAgainstLocalActors(
        actual: List<Hero>,
        localActors: List<LocalActor>
    ) {
        verifyListsHaveSameSize(actual, localActors)
        localActors.forEachIndexed { index, localActor ->
            val actor = actual[index]
            verifyActorAgainstLocalActor(actor, localActor)
        }
    }

    private fun verifyActorAgainstLocalActor(
        actual: Hero,
        localActor: LocalActor
    ) {
        Assert.assertEquals(localActor.id, actual.id)
        Assert.assertEquals(localActor.name, actual.name)
        Assert.assertEquals(localActor.status, actual.status)
        Assert.assertEquals(localActor.species, actual.species)
        Assert.assertEquals(localActor.type, actual.type)
        Assert.assertEquals(localActor.gender, actual.gender)
        Assert.assertEquals(localActor.image, actual.image)
    }

    fun verifyLocalActorsAgainstActors(
        actual: List<LocalActor>,
        heroes: List<Hero>
    ) {
        verifyListsHaveSameSize(actual, heroes)
        heroes.forEachIndexed { index, item ->
            val localItem = actual[index]
            verifyLocalActorAgainstActor(localItem, item)
        }
    }

    private fun verifyLocalActorAgainstActor(
        actual: LocalActor,
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

