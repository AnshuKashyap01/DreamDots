package com.example.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//Step 1 define @entity()ewq
@Entity(tableName = "wish-table") //every single wish will become a  Row
data class Wish(
    @PrimaryKey(autoGenerate = true)  //auto generate will increse id by 1 everytime it generate
    val id : Long = 0L,
    @ColumnInfo(name = "Wish-Title")
    val title : String,
    @ColumnInfo("Wish-Description")
    val description : String = ""
)


