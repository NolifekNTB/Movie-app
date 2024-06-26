package com.example.movieapp.features.Profile.ui.screens.Subscribe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.shared.TopBar

@Composable
fun Subscribe(onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TopBar(title = "") {
            onClick("back")
        }
        MainTitle()
        PremiumBox("monthly"){ where ->
            onClick(where)
        }
        Spacer(modifier = Modifier.height(15.dp))
        PremiumBox("yearly"){ where ->
            onClick(where)
        }
    }
}

@Composable
fun MainTitle(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Subscribe to Premium",
            fontSize = 25.sp,
            color = Color.Green,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.5.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Enjoy watching Full-HD animes. without restrictions and without ads",
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            letterSpacing = 0.5.sp,
            modifier = Modifier.width(300.dp)
        )
    }
}

@Composable
fun PremiumBox(monthOrYearly: String, onClick: (String) -> Unit){
    Card(
        modifier = Modifier
            .size(350.dp, 300.dp)
            .padding(top = 15.dp)
            .clickable {
                onClick("next")
            },
        border = BorderStroke(2.dp, Color.Green),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ){
        PremiumBoxElements(monthOrYearly)
    }
}

@Composable
fun PremiumBoxElements(monthOrYearly: String) {
    Box(Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MainIcon()
            Price(monthOrYearly)
            Divider(thickness = 1.dp, modifier = Modifier.padding(20.dp, 10.dp))
            AdvantagesOfBuy("Watch all you want. Ad-free.")
            AdvantagesOfBuy("Allows streaming of 4K.")
            AdvantagesOfBuy("Video & Audio Quality is Better.")
        }
    }
}

@Composable
fun MainIcon() {
    Icon(
        painter = painterResource(id = R.drawable.subscribe_crown),
        contentDescription = null,
        tint = Color.Green,
        modifier = Modifier
            .size(50.dp)
    )
}

@Composable
fun Price(monthOrYearly: String) {
    val price = if (monthOrYearly == "monthly") "$9.99" else "$99.99"
    val monthYear = if (monthOrYearly == "monthly") "/Monthly" else "/Yearly"

    Row(verticalAlignment = Alignment.CenterVertically){
        Text(
            text = price, fontSize = 25.sp, color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
        Text(text = monthYear)
    }
}

@Composable
fun AdvantagesOfBuy(text: String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 35.dp, top = 10.dp),
        horizontalArrangement = Arrangement.Start) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Checkmark",
            tint = Color.Green)
        Spacer(modifier = Modifier.width(15.dp))
        Text(text = text)
    }
}





















