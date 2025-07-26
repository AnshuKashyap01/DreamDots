package com.example.mywishlistapp


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mywishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {

    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    if (id!= 0L) {
    val wish = viewModel.getAllWishbyId(id).collectAsState(initial= Wish(0L,"",""))
    viewModel.wishTitleState = wish.value.title
     viewModel.wishDescriptionState = wish.value.description
    } else{
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""

    }


        Spacer(Modifier.height(40.dp))

        Scaffold(modifier = Modifier.statusBarsPadding(),
            scaffoldState = scaffoldState,
            topBar = { AppBarView( title = if (id != 0L) "Update Wish" else "Add Wish"){navController.navigateUp()} }
        ) {

            Column(
                modifier = Modifier.padding(it).wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.height(10.dp))
                WishTextField(
                    label = "Title",
                    value = viewModel.wishTitleState,
                    onValueChanged = {
                        viewModel.onWishTitleChange(it)
                    })

                Spacer(modifier = Modifier.height(10.dp))
                WishTextField(
                    label = "Description",
                    value = viewModel.wishDescriptionState,
                    onValueChanged = {
                        viewModel.onWishDescriptionChange(it)
                    })

                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        if (viewModel.wishTitleState.isNotEmpty() &&
                            viewModel.wishDescriptionState.isNotEmpty()
                        ) {
                            if (id != 0L) {
                                viewModel.updatewish(
                                    Wish(
                                        id = id,
                                        title = viewModel.wishTitleState.trim(),
                                        description = viewModel.wishDescriptionState.trim()
                                    )
                                )
                            } else {
                                viewModel.addwish(
                                    Wish(
                                        title = viewModel.wishTitleState.trim(),
                                        description = viewModel.wishDescriptionState.trim()
                                    )
                                )
                            }
                        }

                        scope.launch {
                            navController.navigateUp()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (id != 0L) Color(0xFFFFDDE5) else Color(0xFFFFDDE5),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = if (id != 0L) "Update Wish" else "Add Wish",
                        style = TextStyle(fontSize = 18.sp),
                    )
                }

            }
        }
    }




@Composable
fun WishTextField(
    label : String,
    value : String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(value = value ,
        onValueChanged  ,
        label = { Text(text =  label, color = Color.Black ) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedBorderColor = Color.Blue,
            unfocusedLabelColor = Color.Black,
            cursorColor = Color.Black,
            focusedLabelColor = Color.Blue,
            unfocusedBorderColor = Color.Black
        )

    )
}


@Preview
@Composable
fun WishTest(){
    WishTextField(label =  "text", value = "text", onValueChanged = {})
}