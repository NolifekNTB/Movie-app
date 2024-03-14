package com.example.movieapp.Profile.ui.ProfileScreens.Subscribe.Payment

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
import com.example.movieapp.Profile.logic.ProfileViewModel
import com.example.movieapp.R
import com.example.movieapp.core.other.TopBar

@Composable
fun payment(viewModel: ProfileViewModel, onClick: (String) -> Unit) {
    val cardNumber = viewModel.cardValue.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(name = "Payment") {
            onClick("back")
        }
        Column(
            modifier = Modifier.padding(20.dp, 0.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Select the payment mehtod you want to use.")
            Spacer(modifier = Modifier.height(15.dp))
            paymentMethods("PayPal", viewModel)
            paymentMethods("GooglePay", viewModel)
            paymentMethods("ApplePay", viewModel)
            if(cardNumber.length >= 1){
                paymentMethods(cardNumber, viewModel)
            }
            addNewCard(){where ->
                onClick(where)
            }
        }
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
}

@Composable
fun paymentMethods(name: String, viewModel: ProfileViewModel) {

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
                CircleButton(name, viewModel)
            }
        }
    }
}

@Composable
fun addNewCard(onClick: (String) -> Unit){
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











