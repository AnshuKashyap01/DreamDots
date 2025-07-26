package com.example.mywishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //This will insert or add a new wish and onConflict will take care of duplicate entry
    abstract suspend fun addAWish(wishEntity: Wish)

    //Loads all wishes from wish table
    @Query("Select * from `wish-table`")  //Are wo  hi mySQL jaisa scene hai kuch
    abstract   fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun updateWish(wishEntity: Wish)

    @Delete
    abstract suspend  fun deleteWish(wishEntity: Wish)

    @Query("Select * from `wish-table` where id = :id")
    abstract   fun getAllWisheById(id: Long): Flow<Wish> // it will return one particular wish with that ID




}