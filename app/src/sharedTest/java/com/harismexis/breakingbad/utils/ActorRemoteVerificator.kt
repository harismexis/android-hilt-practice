package com.harismexis.breakingbad.utils

import com.harismexis.breakingbad.datamodel.domain.Actor
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteActor
import org.junit.Assert

class ActorRemoteVerificator {

    fun verifyActorsAgainstRemoteActors(
        actual: List<Actor>,
        remoteActors: List<RemoteActor?>
    ) {
        remoteActors.forEachIndexed lit@{ _, remoteActor ->
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
        remoteActor: RemoteActor
    ) {
        Assert.assertEquals(remoteActor.id, actual.id)
        Assert.assertEquals(remoteActor.name, actual.name)
        Assert.assertEquals(remoteActor.status, actual.status)
        Assert.assertEquals(remoteActor.species, actual.species)
        Assert.assertEquals(remoteActor.status, actual.status)
        Assert.assertEquals(remoteActor.type, actual.type)
        Assert.assertEquals(remoteActor.gender, actual.gender)
        Assert.assertEquals(remoteActor.image, actual.image)
    }

}

