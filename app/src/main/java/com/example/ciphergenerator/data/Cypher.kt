package com.example.ciphergenerator.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "cypher_data")
data class Cypher (
    @PrimaryKey(autoGenerate = true) val id : Int,
    val name : String,
    val cipher : String
    //TODO
    // 90 Day Password reset feature
    // val date : String
)