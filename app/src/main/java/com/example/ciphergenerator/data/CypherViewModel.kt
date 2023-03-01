package com.example.ciphergenerator.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CypherViewModel( application: Application ): AndroidViewModel(application){

    private val readAllData : LiveData<List<Cypher>>
    private val repository : CypherRepository

    init {
        val cypherDao = CypherDatabase.getDatabase(application).cypherDao()
        repository = CypherRepository(cypherDao)
        readAllData = repository.readAllData
    }

    fun addCypher (cypher: Cypher) {
        //Running addCypher on a background thread
        viewModelScope.launch (Dispatchers.IO) {
            repository.addCypher(cypher)
        }
    }
}