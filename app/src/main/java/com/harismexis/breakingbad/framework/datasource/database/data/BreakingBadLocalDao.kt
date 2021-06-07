package com.harismexis.breakingbad.framework.datasource.database.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harismexis.breakingbad.framework.datasource.database.table.LocalActor

@Dao
interface BreakingBadLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(items: List<LocalActor>)

    @Query("SELECT * FROM rick_and_morty_character_table WHERE id = :itemId")
    suspend fun getActorById(itemId: Int): LocalActor?

    @Query("SELECT * FROM rick_and_morty_character_table")
    suspend fun getAllActors(): List<LocalActor?>?

    @Query("DELETE FROM rick_and_morty_character_table")
    suspend fun deleteAllActors()

}
