package com.harismexis.breakingbad.utils

import com.harismexis.breakingbad.datamodel.domain.Actor
import com.harismexis.breakingbad.framework.datasource.database.table.LocalActor
import org.junit.Assert

class ActorLocalVerificator {

    fun verifyActorsAgainstLocalActors(
        actual: List<Actor>,
        localActors: List<LocalActor>
    ) {
        verifyListsHaveSameSize(actual, localActors)
        localActors.forEachIndexed { index, localActor ->
            val actor = actual[index]
            verifyActorAgainstLocalActor(actor, localActor)
        }
    }

    private fun verifyActorAgainstLocalActor(
        actual: Actor,
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
        actors: List<Actor>
    ) {
        verifyListsHaveSameSize(actual, actors)
        actors.forEachIndexed { index, item ->
            val localItem = actual[index]
            verifyLocalActorAgainstActor(localItem, item)
        }
    }

    private fun verifyLocalActorAgainstActor(
        actual: LocalActor,
        actor: Actor
    ) {
        Assert.assertEquals(actor.id, actual.id)
        Assert.assertEquals(actor.name, actual.name)
        Assert.assertEquals(actor.status, actual.status)
        Assert.assertEquals(actor.species, actual.species)
        Assert.assertEquals(actor.status, actual.status)
        Assert.assertEquals(actor.type, actual.type)
        Assert.assertEquals(actor.gender, actual.gender)
        Assert.assertEquals(actor.image, actual.image)
    }

}

