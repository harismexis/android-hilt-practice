package com.harismexis.hiltproject.parser

import com.harismexis.hiltproject.datamodel.domain.Hero
import com.harismexis.hiltproject.framework.datasource.network.model.RemoteHeros
import com.harismexis.hiltproject.framework.extensions.hero.toItems
import com.harismexis.hiltproject.utils.toClassObject

class MockHerosProvider(private val reader: BaseFileReader) {

    companion object {

        const val EXPECTED_NUM_HEROS_WHEN_ALL_IDS_VALID = 10
        const val EXPECTED_NUM_HEROS_WHEN_3_IDS_INVALID = 7
        const val EXPECTED_NUM_HEROS_WHEN_6_IDS_INVALID = 4
        const val EXPECTED_NUM_HEROS_WHEN_SOME_EMPTY = 8
        const val EXPECTED_NUM_HEROS_WHEN_NO_DATA = 0

        const val FILE_10_VALID_HEROS =
            "remote-10-valid-heros.json"
        const val FILE_10_HEROS_BUT_6_IDS_INVALID =
            "remote-10-heros-6-ids-invalid.json"
        const val FILE_10_HEROS_BUT_3_IDS_INVALID =
            "remote-10-heros-3-ids-invalid.json"
        const val FILE_10_HEROS_BUT_TWO_EMPTY =
            "remote-10-heros-2-items-empty.json"
        const val FILE_10_HEROS_ALL_IDS_INVALID =
            "remote-10-heros-all-ids-invalid.json"
        const val FILE_EMPTY_JSON =
            "remote-empty.json"
    }

    // core models
    fun getMockHerosWhenJsonHasAllItemsValid(): List<Hero> =
        getMockRemoteHerosWhenJsonHasAllIdsValid().toItems()

    fun getMockHerosWhenJsonHas6InvalidIds(): List<Hero> =
        getMockRemoteHerosWhenJsonHas6InvalidIds().toItems()

    fun getMockHerosWhenJsonHas3InvalidIds(): List<Hero> =
        getMockRemoteHerosWhenJsonHas3InvalidIds().toItems()

    fun getMockHerosWhenJsonHasSomeEmptyItems(): List<Hero> =
        getMockRemoteHerosWhenJsonHasSomeEmptyItems().toItems()

    fun getMockHerosWhenJsonHasAllIdsInvalid(): List<Hero> =
        getMockRemoteHerosWhenJsonHasAllIdsInvalid().toItems()

    fun getMockHerosWhenJsonIsEmpty(): List<Hero> =
        getMockRemoteHerosWhenJsonIsEmpty().toItems()

    // remote models
    fun getMockRemoteHerosWhenJsonHasAllIdsValid(): RemoteHeros =
        getMockRemoteHeros(getMockDataAllIdsValid())

    fun getMockRemoteHerosWhenJsonHas6InvalidIds(): RemoteHeros =
        getMockRemoteHeros(getMockData6IdsInvalid())

    fun getMockRemoteHerosWhenJsonHas3InvalidIds(): RemoteHeros =
        getMockRemoteHeros(getMockData3IdsInvalid())

    fun getMockRemoteHerosWhenJsonHasSomeEmptyItems(): RemoteHeros =
        getMockRemoteHeros(getMockDataSomeItemsEmpty())

    fun getMockRemoteHerosWhenJsonHasAllIdsInvalid(): RemoteHeros =
        getMockRemoteHeros(getMockDataAllIdsInvalid())

    fun getMockRemoteHerosWhenJsonIsEmpty(): RemoteHeros =
        getMockRemoteHeros(getMockDataEmptyJsonArray())

    // raw json string
    private fun getMockDataAllIdsValid(): String =
        reader.getFileAsString(FILE_10_VALID_HEROS)

    private fun getMockData6IdsInvalid(): String =
        reader.getFileAsString(FILE_10_HEROS_BUT_6_IDS_INVALID)

    private fun getMockData3IdsInvalid(): String =
        reader.getFileAsString(FILE_10_HEROS_BUT_3_IDS_INVALID)

    private fun getMockDataSomeItemsEmpty(): String =
        reader.getFileAsString(FILE_10_HEROS_BUT_TWO_EMPTY)

    private fun getMockDataAllIdsInvalid(): String =
        reader.getFileAsString(FILE_10_HEROS_ALL_IDS_INVALID)

    private fun getMockDataEmptyJsonArray(): String =
        reader.getFileAsString(FILE_EMPTY_JSON)

    private fun getMockRemoteHeros(
        text: String
    ): RemoteHeros {
        return toClassObject(text)
    }

}