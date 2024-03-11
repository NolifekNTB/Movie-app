package com.example.movieapp.Profile.ui.ProfileScreens.Subscribe

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.Profile.ui.ProfileScreens.Subscribe.Payment.CircleButton
import com.example.movieapp.R
import com.example.movieapp.core.other.TopBar

@Preview
@Composable
fun reviewSummaryPreview(onClick: () -> Unit = {}) {
    reviewSummary { onClick() }
}

@Composable
fun reviewSummary(onClick: () -> Unit) {
    val openDialog = remember {
        mutableStateOf(false)
    }

    if(openDialog.value){
        AlertDialogExample(openDialog)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(name = "ReviewSummary"){
            onClick()
        }
        premiumBox(monthOrYearly = "monthly"){_ -> }
        review()
        card()
        confirmButton(openDialog)
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun review() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(20.dp)
    ){
        Column(Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            oneRow(name = "Amount", price = "$9.99")
            oneRow(name = "Tax", price = "$1.99")
            Divider(thickness = 1.dp, color = Color.LightGray, modifier = Modifier.padding(10.dp, 0.dp))
            oneRow(name = "Total", price = "$11.99")
        }
    }
}

@Composable
fun oneRow(name: String, price: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name)
        Text(text = price)
    }
}

@Composable
fun card() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(20.dp, 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 10.dp),
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
                        painter = painterResource(id = R.drawable.mastercard),
                        contentDescription = "",
                        modifier = Modifier.size(50.dp))
                    Text(
                        text = "7819 1209 1241",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 10.dp))
                }
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Change", color = Color.Green)
                }
            }
        }
    }
}

@Composable
fun confirmButton(openDialog: MutableState<Boolean>) {
    FilledTonalButton(
        onClick = { openDialog.value = true },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0x2500FF00),
            contentColor = Color(0xFF00FF00)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(20.dp, 0.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Confirm Payment")
        }
    }
}

@Composable
fun AlertDialogExample(openDialog: MutableState<Boolean>) {
    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.crown),
                contentDescription = "Example Icon",
                tint = Color.Green,
                modifier = Modifier.size(100.dp)
            )
        },
        title = {
            Text(text = "Congrulations!", color = Color.Green)
        },
        text = {
            Text(text = "You have successfully subscribed 1 month premium. Enjoy the benefits!")
        },
        onDismissRequest = {

        },
        confirmButton = {
            FilledTonalButton(
                onClick = {
                    openDialog.value = false
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0x2500FF00),
                    contentColor = Color(0xFF00FF00)
                )
            ) {
                Text("OK")
            }
        },
        dismissButton = {

        }
    )
}