package com.example.ciphergenerator.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CypherDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCipher(cypher : Cypher)

    @Query("SELECT * FROM cypher_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<Cypher>>
}