package com.example.movieapp.features.Profile.ui.screens.Subscribe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.features.Profile.domain.ProfileViewModel
import com.example.movieapp.shared.TopBar

@Composable
fun Payment(viewModel: ProfileViewModel, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(name = "Payment") { onClick("back") }
        PaymentMethods(viewModel) {where ->
            onClick(where)
        }
        ContinueButton(){where ->
            onClick(where)
        }
    }
}

@Composable
fun PaymentMethods(viewModel: ProfileViewModel, onClick: (String) -> Unit) {
    val cardNumber = viewModel.cardValue.collectAsState().value

    Column(
        modifier = Modifier.padding(20.dp, 0.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Select the payment mehtod you want to use.")
        Spacer(modifier = Modifier.height(15.dp))

        PaymentMethodsElement("PayPal", viewModel)
        PaymentMethodsElement("GooglePay", viewModel)
        PaymentMethodsElement("ApplePay", viewModel)

        if(cardNumber.isNotEmpty()){
            PaymentMethodsElement(cardNumber, viewModel)
        }
        AddNewCard(){ where ->
            onClick(where)
        }
    }
}

@Composable
fun PaymentMethodsElement(name: String, viewModel: ProfileViewModel) {
    var icon = 0
    when(name){
        "PayPal" -> icon = R.drawable.payment_paypal
        "GooglePay" -> icon = R.drawable.payment_google
        "ApplePay" -> icon = R.drawable.payment_appleicon
        else -> icon = R.drawable.payment_mastercard
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(0.dp, 10.dp)
            .clickable {
                viewModel.whichMethodIsClicked(name)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(25.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            contentAlignment = Alignment.CenterStart
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageWithText(icon, name)
                CircleButton(name, viewModel)
            }
        }
    }
}

@Composable
fun ImageWithText(icon: Int, name: String) {
    Row(verticalAlignment = Alignment.CenterVertically){
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(50.dp))
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 10.dp))
    }
}


@Composable
fun CircleButton(name: String, viewModel: ProfileViewModel){
    val clicked = viewModel.clickedDay.collectAsState().value

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .size(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if(clicked == name) Color.Green else Color.White
        ),
        border = BorderStroke(2.dp, Color.Green)
    ){
        Text(text = "", color = Color.White, modifier = Modifier.padding(1.dp))
    }
}


@Composable
fun AddNewCard(onClick: (String) -> Unit){
    Spacer(modifier = Modifier.height(20.dp))

    FilledTonalButton(
        onClick = { onClick("addNewCard") },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0x2500FF00),
            contentColor = Color(0xFF00FF00)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Add new card")
        }
    }
}

@Composable
fun ContinueButton(onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(20.dp, 20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {
        Continue(){where ->
            onClick(where)
        }
    }
}

@Composable
fun Continue(onClick: (String) -> Unit){
    FilledTonalButton(
        onClick = { onClick("continue") },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF00FF00),
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Continue")
        }
    }
}











