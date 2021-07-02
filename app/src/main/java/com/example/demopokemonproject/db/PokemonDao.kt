package com.example.demopokemonproject.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listOfPokemon: List<PokemonDB>): Completable

    @Query("Select * from pokemon")
    fun getAll(): Flowable<List<PokemonDB>>
}