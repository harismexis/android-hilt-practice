package com.harismexis.breakingbad.parser

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteHero
import java.lang.reflect.Type
import java.util.*

class MockHerosParser(private val parser: BaseFileParser) {

    companion object {

        const val EXPECTED_NUM_ACTORS_WHEN_ALL_IDS_VALID = 5
        const val EXPECTED_NUM_ACTORS_WHEN_SOME_IDS_INVALID = 2
        const val EXPECTED_NUM_ACTORS_WHEN_SOME_EMPTY = 3
        const val EXPECTED_NUM_ACTORS_WHEN_NO_DATA = 0
        const val EXPECTED_NUM_ACTORS_WHEN_SEARCH_BY_NAME_LIKE_WALTER = 2
        const val EXPECTED_NUM_ACTORS_WHEN_SEARCH_BY_NAME_LIKE_SALA = 4

        const val FILE_FIVE_VALID_ACTORS =
            "remote-5-valid-actors.json"
        const val FILE_FIVE_ACTORS_BUT_THREE_IDS_INVALID =
            "remote-5-actors-3-ids-invalid.json"
        const val FILE_FIVE_ACTORS_BUT_TWO_EMPTY =
            "remote-5-actors-2-items-empty.json"
        const val FILE_FIVE_ACTORS_ALL_IDS_INVALID =
            "remote-5-actors-all-ids-invalid.json"
        const val FILE_EMPTY_JSON =
            "remote-empty.json"
        const val FILE_SEARCH_ACTORS_BY_NAME_LIKE_WALTER =
            "remote-search-actors-by-name-like-walter.json"
        const val FILE_SEARCH_ACTORS_BY_NAME_LIKE_SALA =
            "remote-search-actors-by-name-like-sala.json"
    }

    // local models
    //fun getMockLocalActor(): LocalActor = getMockLocalActorsWhenJsonHasAllItemsValid()[0]

//    fun getMockLocalActorsWhenJsonHasAllItemsValid(): List<LocalActor> =
//        getMockActorsWhenJsonHasAllItemsValid().toLocalItems()
//
//    fun getMockLocalActorsWhenJsonHasSomeInvalidIds(): List<LocalActor> =
//        getMockActorsWhenJsonHasSomeInvalidIds().toLocalItems()
//
//    fun getMockLocalActorsWhenJsonHasAllIdsInvalid(): List<LocalActor> =
//        getMockActorsWhenJsonHasAllIdsInvalid().toLocalItems()
//
//    fun getMockLocalActorsSearchByNameLikeWalter(): List<LocalActor> =
//        getMockActorsSearchByNameLikeWalter().toLocalItems()
//
//    fun getMockLocalActorsSearchByNameLikeSala(): List<LocalActor> =
//        getMockActorsSearchByNameLikeSala().toLocalItems()

    // core models
    //fun getMockActor(): Actor = getMockActorsWhenJsonHasAllItemsValid()[0]

//    fun getMockActorsWhenJsonHasAllItemsValid(): List<Actor> =
//        getMockRemoteActorsWhenJsonHasAllIdsValid().toItems()
//
//    fun getMockActorsWhenJsonHasSomeInvalidIds(): List<Actor> =
//        getMockRemoteActorsWhenJsonHasSomeInvalidIds().toItems()
//
//    fun getMockActorsWhenJsonHasSomeEmptyItems(): List<Actor> =
//        getMockRemoteActorsWhenJsonHasSomeEmptyItems().toItems()
//
//    fun getMockActorsWhenJsonHasAllIdsInvalid(): List<Actor> =
//        getMockRemoteActorsWhenJsonHasAllIdsInvalid().toItems()
//
//    fun getMockActorsWhenJsonIsEmpty(): List<Actor> =
//        getMockRemoteActorsWhenJsonIsEmpty().toItems()
//
//    fun getMockActorsSearchByNameLikeWalter(): List<Actor> =
//        getMockRemoteActorsSearchByNameLikeWalter().toItems()
//
//    fun getMockActorsSearchByNameLikeSala(): List<Actor> =
//        getMockRemoteActorsSearchByNameLikeSala().toItems()

    // remote models
    fun getMockRemoteActorsWhenJsonHasAllIdsValid(): List<RemoteHero> =
        getMockRemoteActors(getMockDataAllIdsValid())

    fun getMockRemoteActorsWhenJsonHasSomeInvalidIds(): List<RemoteHero> =
        getMockRemoteActors(getMockDataSomeIdsInvalid())

    fun getMockRemoteActorsWhenJsonHasSomeEmptyItems(): List<RemoteHero> =
        getMockRemoteActors(getMockDataSomeItemsEmpty())

    fun getMockRemoteActorsWhenJsonHasAllIdsInvalid(): List<RemoteHero> =
        getMockRemoteActors(getMockDataAllIdsInvalid())

    fun getMockRemoteActorsWhenJsonIsEmpty(): List<RemoteHero> =
        getMockRemoteActors(getMockDataEmptyJsonArray())

    fun getMockRemoteActorsSearchByNameLikeWalter(): List<RemoteHero> =
        getMockRemoteActors(getMockDataSearchByNameLikeWalter())

    fun getMockRemoteActorsSearchByNameLikeSala(): List<RemoteHero> =
        getMockRemoteActors(getMockDataSearchByNameLikeSala())

    // raw json string
    private fun getMockDataAllIdsValid(): String =
        parser.getFileAsString(FILE_FIVE_VALID_ACTORS)

    private fun getMockDataSomeIdsInvalid(): String =
        parser.getFileAsString(FILE_FIVE_ACTORS_BUT_THREE_IDS_INVALID)

    private fun getMockDataSomeItemsEmpty(): String =
        parser.getFileAsString(FILE_FIVE_ACTORS_BUT_TWO_EMPTY)

    private fun getMockDataAllIdsInvalid(): String =
        parser.getFileAsString(FILE_FIVE_ACTORS_ALL_IDS_INVALID)

    private fun getMockDataEmptyJsonArray(): String =
        parser.getFileAsString(FILE_EMPTY_JSON)

    private fun getMockDataSearchByNameLikeWalter(): String =
        parser.getFileAsString(FILE_SEARCH_ACTORS_BY_NAME_LIKE_WALTER)

    private fun getMockDataSearchByNameLikeSala(): String =
        parser.getFileAsString(FILE_SEARCH_ACTORS_BY_NAME_LIKE_SALA)

    // utils
    private fun getMockRemoteActors(
        text: String
    ): List<RemoteHero> {
        return convertToRemoteActors(text)
    }

    private fun convertToRemoteActors(jsonFeed: String?): List<RemoteHero> {
        val gson = GsonBuilder().setLenient().create()
        val type: Type = object : TypeToken<ArrayList<RemoteHero>>() {}.type
        return gson.fromJson(jsonFeed, type)
    }

}