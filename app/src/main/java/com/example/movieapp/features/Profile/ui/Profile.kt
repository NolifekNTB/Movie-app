package com.example.movieapp.features.Profile.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapp.features.Profile.ui.composables.PremiumDescription
import com.example.movieapp.features.Profile.ui.composables.PremiumFirstIcon
import com.example.movieapp.features.Profile.ui.composables.PremiumSecondIcon
import com.example.movieapp.features.Profile.ui.composables.ProfileImage
import com.example.movieapp.features.Profile.ui.composables.ProfileInformation
import com.example.movieapp.features.Profile.ui.composables.SettingsList
import com.example.movieapp.shared.TopBar

@Composable
fun Profile(onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { 
        TopBar(name = "Profile") { onClick("back") }
        ImageAndInformation()
        BuyPremium(){ where ->
            onClick(where)
        }
        Settings(){ where ->
            onClick(where)
        }
    }
}

@Composable
fun ImageAndInformation(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Left,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp)
    ) {
        ProfileImage()
        Spacer(Modifier.width(30.dp))
        ProfileInformation()
    }
}

@Composable
fun BuyPremium(onClick: (String) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Gray
            ),
            border = BorderStroke(2.dp, Color.Green),
            shape = RoundedCornerShape(25.dp)
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        onClick("premium")
                    }
            ){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    PremiumFirstIcon()
                    Spacer(modifier = Modifier.width(15.dp))
                    PremiumDescription(Modifier.weight(1f))
                    PremiumSecondIcon()
                }
            }
        }
    }
}

@Composable
fun Settings(onClick: (String) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 0.dp)
    ){
        repeat(9){ index ->
            SettingsList(index){ where ->
                onClick(where)
            }
        }
    }
}
