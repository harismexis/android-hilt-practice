package com.harismexis.hiltproject.parser

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.harismexis.hiltproject.datamodel.domain.Hero
import com.harismexis.hiltproject.framework.datasource.database.table.LocalHero
import com.harismexis.hiltproject.framework.datasource.network.model.RemoteHero
import com.harismexis.hiltproject.framework.datasource.network.model.RemoteHeros
import com.harismexis.hiltproject.framework.extensions.hero.toItems
import com.harismexis.hiltproject.framework.extensions.hero.toLocalItems
import java.lang.reflect.Type
import java.util.*

class MockHerosParser(private val parser: BaseFileParser) {

    companion object {

        const val EXPECTED_NUM_HEROS_WHEN_ALL_IDS_VALID = 10
        const val EXPECTED_NUM_HEROS_WHEN_SOME_IDS_INVALID = 2
        const val EXPECTED_NUM_HEROS_WHEN_SOME_EMPTY = 8
        const val EXPECTED_NUM_HEROS_WHEN_NO_DATA = 0

        const val FILE_FIVE_VALID_HEROS =
            "remote-10-valid-heros.json"
        const val FILE_FIVE_HEROS_BUT_THREE_IDS_INVALID =
            "remote-10-heros-3-ids-invalid.json"
        const val FILE_FIVE_HEROS_BUT_TWO_EMPTY =
            "remote-10-heros-2-items-empty.json"
        const val FILE_FIVE_HEROS_ALL_IDS_INVALID =
            "remote-10-heros-all-ids-invalid.json"
        const val FILE_EMPTY_JSON =
            "remote-empty.json"
    }

    // local models
    fun getMockLocalHero(): LocalHero = getMockLocalHerosWhenJsonHasAllItemsValid()[0]

    fun getMockLocalHerosWhenJsonHasAllItemsValid(): List<LocalHero> =
        getMockHerosWhenJsonHasAllItemsValid().toLocalItems()

    fun getMockLocalHerosWhenJsonHasSomeInvalidIds(): List<LocalHero> =
        getMockHerosWhenJsonHasSomeInvalidIds().toLocalItems()

    fun getMockLocalHerosWhenJsonHasAllIdsInvalid(): List<LocalHero> =
        getMockHerosWhenJsonHasAllIdsInvalid().toLocalItems()

    // core models
    fun getMockHero(): Hero = getMockHerosWhenJsonHasAllItemsValid()[0]

    fun getMockHerosWhenJsonHasAllItemsValid(): List<Hero> =
        getMockRemoteHerosWhenJsonHasAllIdsValid().toItems()

    fun getMockHerosWhenJsonHasSomeInvalidIds(): List<Hero> =
        getMockRemoteHerosWhenJsonHasSomeInvalidIds().toItems()

    fun getMockHerosWhenJsonHasSomeEmptyItems(): List<Hero> =
        getMockRemoteHerosWhenJsonHasSomeEmptyItems().toItems()

    fun getMockHerosWhenJsonHasAllIdsInvalid(): List<Hero> =
        getMockRemoteHerosWhenJsonHasAllIdsInvalid().toItems()

    fun getMockHerosWhenJsonIsEmpty(): List<Hero> =
        getMockRemoteHerosWhenJsonIsEmpty().toItems()

    // remote models
    fun getMockRemoteHerosWhenJsonHasAllIdsValid(): RemoteHeros =
        getMockRemoteHeros(getMockDataAllIdsValid())

    fun getMockRemoteHerosWhenJsonHasSomeInvalidIds(): RemoteHeros =
        getMockRemoteHeros(getMockDataSomeIdsInvalid())

    fun getMockRemoteHerosWhenJsonHasSomeEmptyItems(): RemoteHeros =
        getMockRemoteHeros(getMockDataSomeItemsEmpty())

    fun getMockRemoteHerosWhenJsonHasAllIdsInvalid(): RemoteHeros =
        getMockRemoteHeros(getMockDataAllIdsInvalid())

    fun getMockRemoteHerosWhenJsonIsEmpty(): RemoteHeros =
        getMockRemoteHeros(getMockDataEmptyJsonArray())

    // raw json string
    private fun getMockDataAllIdsValid(): String =
        parser.getFileAsString(FILE_FIVE_VALID_HEROS)

    private fun getMockDataSomeIdsInvalid(): String =
        parser.getFileAsString(FILE_FIVE_HEROS_BUT_THREE_IDS_INVALID)

    private fun getMockDataSomeItemsEmpty(): String =
        parser.getFileAsString(FILE_FIVE_HEROS_BUT_TWO_EMPTY)

    private fun getMockDataAllIdsInvalid(): String =
        parser.getFileAsString(FILE_FIVE_HEROS_ALL_IDS_INVALID)

    private fun getMockDataEmptyJsonArray(): String =
        parser.getFileAsString(FILE_EMPTY_JSON)

    // utils
    private fun getMockRemoteHeros(
        text: String
    ): RemoteHeros {
        return convertToModel(text)
    }

    private fun convertToRemoteHeros(jsonFeed: String?): List<RemoteHero> {
        val gson = GsonBuilder().setLenient().create()
        val type: Type = object : TypeToken<ArrayList<RemoteHero>>() {}.type
        return gson.fromJson(jsonFeed, type)
    }

    inline fun <reified T> convertToModel(jsonString: String?): T {
        val gson = GsonBuilder().setLenient().create()
        val json: JsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        return Gson().fromJson(json, T::class.java)
    }

}