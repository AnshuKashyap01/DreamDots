package com.example.mywishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywishlistapp.data.Wish
import com.example.mywishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository
): ViewModel( ) {

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")


    fun onWishTitleChange(newString: String){
        wishTitleState = newString
    }

    fun onWishDescriptionChange(newString : String){
        wishDescriptionState = newString
    }
     //Late Initializer
    lateinit var getAllWishes: Flow<List<Wish>> //lateinit yeh promise karta hai compiler ko ki ha variable exist karta abhi nahi thodi der mein ho hi jayega couritine function ke saath jyada rehti hai yeh dikkat

     init {
         viewModelScope.launch {
             getAllWishes = wishRepository.getWish()
         }
     }

    fun addwish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) { //Dispatcher IO will make it more efficient and fast
            wishRepository.addAWish(wish)
        }
    }

    fun getAllWishbyId(id:Long): Flow<Wish>{
        return wishRepository.getAWishById(id)
    }


    fun updatewish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) { //Dispatcher IO will make it more efficient and fast
            wishRepository.updateWish(wish)
        }
    }

    fun deletewish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) { //Dispatcher IO will make it more efficient and fast
            wishRepository.deleteWish(wish)
        }
    }


}