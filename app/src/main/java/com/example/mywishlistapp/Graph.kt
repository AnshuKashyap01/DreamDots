package com.example.mywishlistapp

import android.content.Context

import androidx.room.Room
import com.example.mywishlistapp.data.WishDataBase
import com.example.mywishlistapp.data.WishRepository

object Graph {
    lateinit var dataBase: WishDataBase

    val wishRepository by lazy {   //lazy make sure it will load only when needed not just start of the app
        WishRepository(wishDao = dataBase.wishDao())
    }


    fun provide(context: Context){
        dataBase = Room.databaseBuilder(context, WishDataBase::class.java,"wishlist.db").build()
    }


}