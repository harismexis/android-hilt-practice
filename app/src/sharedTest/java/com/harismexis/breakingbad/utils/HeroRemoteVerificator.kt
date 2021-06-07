package com.harismexis.breakingbad.utils

import com.harismexis.hiltproject.datamodel.domain.Hero
import com.harismexis.hiltproject.framework.datasource.network.model.RemoteHero
import org.junit.Assert

class HeroRemoteVerificator {

    fun verifyActorsAgainstRemoteActors(
        actual: List<Hero>,
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
        actual: Hero,
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

