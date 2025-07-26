package com.example.mywishlistapp

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mywishlistapp.ui.theme.app_bar_color


@Composable
fun AppBarView(
               title: String,
               onBackNavClicked: () -> Unit = {}   // ye ek lambda function hai jo tab chalega jab user back button pe click karega.
) {

    // yeh ek function hai jo Navigating arrow create kar raha hai jo top bar mein hoga jisse back jayenge.
    val backnavigatingIcon: ( @Composable () -> Unit)? =

        if(! title.contains("DreamDots")){
            {
            IconButton(onClick = { onBackNavClicked( ) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
          }
        } else {
            null
        }

    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        elevation = 3.dp,
        backgroundColor = app_bar_color,
        navigationIcon = backnavigatingIcon


    )
}
