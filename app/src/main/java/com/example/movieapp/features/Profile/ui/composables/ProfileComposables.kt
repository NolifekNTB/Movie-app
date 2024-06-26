package com.example.movieapp.features.Profile.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R

@Composable
fun ProfileImage(){
    Card(
        modifier = Modifier
            .size(100.dp),
        shape = CircleShape
    ){
        Box(){
            Image(
                painter = painterResource(id = R.drawable.profile_profilephoto),
                contentDescription = "Profile Photo",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun ProfileInformation(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Janusz Kowalski", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Text(text = "januszkowalski@wp.pl", fontSize = 15.sp)
    }
}

@Composable
fun PremiumFirstIcon(){
    Icon(
        imageVector = Icons.Default.Check,
        contentDescription = "PremiumIcon",
        tint = Color.Green,
        modifier = Modifier.size(66.dp)
    )
}

@Composable
fun PremiumDescription(modifier: Modifier){
    Column(
        modifier = modifier
            .width(250.dp)
    ) {
        Text(text = "Join premium", fontSize = 23.sp, color = Color.Green)
        Text(text = "Enjoy watchin Full-HD animes, without restrictions and " +
                "without ads.", fontSize = 12.sp)
    }
}

@Composable
fun PremiumSecondIcon(){
    Icon(
        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
        contentDescription = "PremiumIcon2",
        tint = Color.Green,
        modifier = Modifier.size(33.dp)
    )
}

@Composable
fun SettingsList(index: Int, onClick: (String) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .clickable {
                onClick("settings")
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SettingsListElement(index)
        SettingsListIfDarkMode(index)
    }
}

@Composable
fun SettingsListElement(index: Int) {
    val name = when(index){
        0 -> "Edit Profile"
        1 -> "Notifications"
        2 -> "Download"
        3 -> "Security"
        4 -> "Language"
        5 -> "Dark Mode"
        6 -> "Help Center"
        7 -> "Privacy Policy"
        8 -> "Log Out"
        else -> "Settings"
    }
    val icon = when(index){
        0 -> R.drawable.profile_person
        1 -> R.drawable.profile_notification
        2 -> R.drawable.profile_download
        3 -> R.drawable.profile_person_security
        4 -> R.drawable.profile_langauge
        5 -> R.drawable.profile_darkmode
        6 -> R.drawable.profile_help
        7 -> R.drawable.profile_privacy
        8 -> R.drawable.profile_logout
        else -> R.drawable.profile_person
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = icon),
            contentDescription = "leadIcon",
            tint = if(index == 8 ) Color.Red else Color.Black,
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = name, fontSize = 18.sp,
            color = if(index == 8 ) Color.Red else Color.Black)
    }
}

@Composable
fun SettingsListIfDarkMode(index: Int) {
    var checked by remember { mutableStateOf(true) }
    if(index == 5){
        Switch(
            checked = checked,
            onCheckedChange = { checked = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color.Green,
                uncheckedThumbColor = Color.LightGray
            )
        )
    }
    else {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "lastIcon",)
    }
}




























