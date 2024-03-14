package com.example.movieapp.Profile.ui.ProfileScreens.Subscribe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.Profile.logic.ProfileViewModel
import com.example.movieapp.R
import com.example.movieapp.core.other.TopBar


@Composable
fun addNewCard(viewModel: ProfileViewModel, onClick: (String) -> Unit) {
    Column(modifier = Modifier.background(Color.White)){
        TopBar(name = "AddNewCard") {
            onClick("back")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            BankCard()
            Divider()
            Field("Card Name", Modifier.fillMaxWidth(), viewModel)
            Field("Card Number", Modifier.fillMaxWidth(), viewModel)
            Row {
                Field("Expiry Date", Modifier.weight(0.5f), viewModel)
                Spacer(modifier = Modifier.width(15.dp))
                Field("CVV", Modifier.weight(0.5f), viewModel)
            }
            AddNewCard(){where ->
                onClick(where)
            }
        }
    }

}

@Composable
fun BankCard(){
    Card(
        modifier = Modifier
            .size(350.dp, 200.dp)
            .padding(bottom = 20.dp),
        shape = RoundedCornerShape(15.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.addnewcard_visa),
            contentDescription = "cardImage",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Field(name: String, modifier: Modifier, viewModel: ProfileViewModel){
    var text by remember {
        mutableStateOf("")
    }

    var keyBoardType = KeyboardType.Text
    when(name){
        "Card Name" -> {
            keyBoardType = KeyboardType.Text
        }
        "Card Number" -> {
            keyBoardType = KeyboardType.Number
            text = text.take(16)
        }
        "Expiry Date" -> {
            keyBoardType = KeyboardType.Text
            text = text.filter { it.isDigit() || it == '/' }
            text = text.take(10)
        }

        "CVV" -> {
            keyBoardType = KeyboardType.Number
            text = text.take(4)
        }
    }

    viewModel.collectValue(text)

    Column(
        modifier =  modifier
            .padding(top = 15.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(10.dp))
        Box(contentAlignment = Alignment.CenterEnd) {
            TextField(
                value = text,
                onValueChange = {text = it},
                placeholder = {if(name == "Expiry Date") Text("01/01/2000")},
                shape = RoundedCornerShape(15.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = keyBoardType
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            if(name == "Expiry Date"){
                Icon(
                    painter = painterResource(id = R.drawable.addnewcard_calendar),
                    contentDescription = "calendarIcon",
                    modifier = Modifier.padding(end = 15.dp))
            }
        }
    }
}

@Composable
fun AddNewCard(onClick: (String) -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        FilledTonalButton(
            onClick = { onClick("add") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00FF00),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "Add")
            }
        }
    }
}

























