package com.example.demopokemonproject.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demopokemonproject.db.AppDatabase.Companion.DATABASE_VERSION

//import com.example.demopokemonproject.db.AppDatabase.Companion.DATABASE_VERSION

@Database(entities = [PokemonDB::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "pokemon_db"

    }

    abstract fun pokemonDao(): PokemonDao

}