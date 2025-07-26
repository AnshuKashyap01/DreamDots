package com.example.mywishlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {

   suspend fun addAWish(wish: Wish){
        wishDao.addAWish(wish)
    }


    fun getWish(): Flow<List<Wish>> = wishDao.getAllWishes() //this is also a way to return a output

    fun getAWishById(id : Long) : Flow<Wish> { //flow waale already hi susupend hote hai
        return wishDao.getAllWisheById(id)
    }

    suspend fun updateWish(wish: Wish){
        wishDao.updateWish(wish)
    }

    suspend fun deleteWish(wish: Wish){
        wishDao.deleteWish(wish)
    }

}

