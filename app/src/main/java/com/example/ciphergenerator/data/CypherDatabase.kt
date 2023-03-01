package com.example.ciphergenerator.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cypher::class], version = 1, exportSchema = false)
abstract class CypherDatabase : RoomDatabase() {

    abstract fun cypherDao(): CypherDao

    companion object {
        @Volatile
        private var INSTANCE: CypherDatabase? = null

        fun getDatabase(context: Context): CypherDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CypherDatabase::class.java,
                        "cipher_database_1"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}