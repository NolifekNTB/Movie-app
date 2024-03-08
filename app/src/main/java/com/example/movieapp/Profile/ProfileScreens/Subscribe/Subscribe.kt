package com.example.movieapp.Profile.ProfileScreens.Subscribe

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
import com.example.movieapp.core.other.TopBar

@Composable
fun subscribe(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TopBar(name = "") { onClick() }
        title()
        premiumBox("monthly")
        Spacer(modifier = Modifier.height(15.dp))
        premiumBox("yearly")
    }
}

@Composable
fun title(){
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
fun premiumBox(monthOrYearly: String){
    val price = if (monthOrYearly == "monthly") "$9.99" else "$99.99"
    val monthYear = if (monthOrYearly == "monthly") "/Monthly" else "/Yearly"

    Card(
        modifier = Modifier
            .size(350.dp, 300.dp)
            .padding(top = 15.dp)
            .clickable {
                       //TODO
            },
        border = BorderStroke(2.dp, Color.Green),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ){
        Box(Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.crown),
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier
                        .size(50.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = price, fontSize = 25.sp, color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = monthYear)

                }
                Divider(thickness = 1.dp, modifier = Modifier.padding(20.dp, 10.dp))
                textWithCheckmark("Watch all you want. Ad-free.")
                textWithCheckmark("Allows streaming of 4K.")
                textWithCheckmark("Video & Audio Quality is Better.")
            }
        }
    }
}

@Composable
fun textWithCheckmark(text: String){
    Row(        modifier = Modifier
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





















