package com.example.mywishlistapp


import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mywishlistapp.data.Wish


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel,
){


        Scaffold( modifier = Modifier.fillMaxSize().statusBarsPadding(),
            containerColor = Color(		0xFFFAD4DE	),
            topBar = { AppBarView( title ="DreamDots",{
                })},
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.padding(20.dp),
                    contentColor = Color.White,
                    backgroundColor = Color.Black
                    ,onClick = {/*Here we add navigation to add screen */
                              navController.navigate(Screen.AddScreen.route + "/0L")
                                }
                ) {

                    Icon(imageVector = Icons.Default.Add, contentDescription = "ADD BUTTON")

                }
            }

        ) {
            val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf()) // we are fetching wishlist in list form from Database
            LazyColumn(modifier = Modifier.fillMaxSize().padding(it)) {
                items(wishlist.value, key = {item->item.id}){
                    //here item is what ever is inside items.
                    item ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                                viewModel.deletewish(item)
                            }
                            true
                        }
                    )

                    SwipeToDismiss(state = dismissState,
                        background = {
                            val color : Color  by animateColorAsState(
                                if (dismissState.dismissDirection == DismissDirection.EndToStart) Color(color = 0xFFFAFAFA) else Color.Transparent ,
                                label = ""
                            )
                            val alignment = Alignment.CenterEnd
                            Box(
                                modifier = Modifier.fillMaxSize().background(color).padding(horizontal = 20.dp),
                                contentAlignment = alignment
                            ){
                                Icon(Icons.Default.Delete,
                                    contentDescription = "Delete Icon",
                                    tint =  Color.White)
                            }
                        },
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(0.25f)},
                        dismissContent = {
                            WishItem(wish=item) {
                                val id = item.id
                                navController.navigate(Screen.AddScreen.route + "/$id")

                            }
                        }
                    )


                }

            }
        }
    }



@Composable

fun WishItem(
    wish: Wish,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFDFDFD) // slightly off-white
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = wish.title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = wish.description,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF666666)
                )
            )
        }
    }
}




























