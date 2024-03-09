package com.example.movieapp.Profile.ui.ProfileScreens.Subscribe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.core.other.TopBar

@Preview
@Composable
fun addNewCardPreview(){
    addNewCard({})
}


@Composable
fun addNewCard(onClick: (String) -> Unit) {
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
        Field("Card Name")
        Field("Card Number")
        Row {
            Field("Expiry Date")
            Spacer(modifier = Modifier.width(20.dp))
            Field("CVV")
        }
    }
}

@Composable
fun BankCard(){
    Card(
        modifier = Modifier
            .size(350.dp, 200.dp)
            .padding(),
        shape = RoundedCornerShape(15.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.visa),
            contentDescription = "cardImage",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Field(name: String){
    var text by remember {
        mutableStateOf("")
    }

    Column() {
        Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = text,
            onValueChange = {text = it},
            shape = RoundedCornerShape(15.dp),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

























