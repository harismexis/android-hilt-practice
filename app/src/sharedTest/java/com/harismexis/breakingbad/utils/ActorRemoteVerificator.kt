package com.harismexis.breakingbad.utils

import com.harismexis.breakingbad.datamodel.domain.Actor
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteHero
import org.junit.Assert

class ActorRemoteVerificator {

    fun verifyActorsAgainstRemoteActors(
        actual: List<Actor>,
        remoteHeroes: List<RemoteHero?>
    ) {
        remoteHeroes.forEachIndexed lit@{ _, remoteActor ->
            if (remoteActor == null) return@lit
            actual.forEachIndexed { _, actor ->
                remoteActor.id?.let {
                    if (it == actor.id) {
                        verifyActorAgainstRemoteActor(actor, remoteActor)
                        return@lit
                    }
                }
            }
        }
    }

    private fun verifyActorAgainstRemoteActor(
        actual: Actor,
        remoteHero: RemoteHero
    ) {
        Assert.assertEquals(remoteHero.id, actual.id)
        Assert.assertEquals(remoteHero.name, actual.name)
        Assert.assertEquals(remoteHero.status, actual.status)
        Assert.assertEquals(remoteHero.species, actual.species)
        Assert.assertEquals(remoteHero.status, actual.status)
        Assert.assertEquals(remoteHero.type, actual.type)
        Assert.assertEquals(remoteHero.gender, actual.gender)
        Assert.assertEquals(remoteHero.image, actual.image)
    }

}

