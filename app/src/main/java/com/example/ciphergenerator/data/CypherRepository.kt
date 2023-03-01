package com.example.ciphergenerator.data

import androidx.lifecycle.LiveData

class CypherRepository(private val cypherDao: CypherDao) {

    val readAllData : LiveData<List<Cypher>> = cypherDao.readAllData()

    suspend fun addCypher(cypher: Cypher) {
        cypherDao.addCipher(cypher)
    }
}