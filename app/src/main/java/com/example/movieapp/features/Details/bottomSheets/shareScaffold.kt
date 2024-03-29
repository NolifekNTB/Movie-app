package com.example.movieapp.features.Details.bottomSheets

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R

@Composable
fun shareDisplayBox(){
    val intentContext = LocalContext.current
    Column(
        modifier = Modifier
            .height(350.dp)
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        shareTitle(title = "Share to", color = Color.Black)
        Divider(thickness = 1.dp, color = Color.LightGray)
        shareElements(intentContext)
    }
}

@Composable
fun shareElements(context: Context){
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for(i in 0..3){
           shareElementsItem(i, context)
        }
    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for(i in 4..7){
            shareElementsItem(i, context)
        }
    }
}

@Composable
fun shareElementsItem(i: Int, context: Context){
    val icons = mutableListOf(
        R.drawable.share_whatsapp,
        R.drawable.share_twitter,
        R.drawable.share_facebook,
        R.drawable.share_instagram,
        R.drawable.share_yahoo,
        R.drawable.share_message,
        R.drawable.share_wechat,
        R.drawable.share_tiktok,
    )
    val names = mutableListOf(
        "WhatsApp",
        "Twitter",
        "Facebook",
        "Instagram",
        "Yahoo",
        "Message",
        "WeChat",
        "TikTok",
    )

    Column {
        Image(
            painterResource(id = icons[i]),
            contentDescription = "",
            modifier = Modifier
                .size(75.dp)
                .padding(15.dp)
                .clickable {
                    intentShare(context, names[i])
                }
        )
        Text(
            text = names[i],
            fontSize = 13.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

fun intentShare(context: Context, whatApp: String){
    var packageName = ""
    when(whatApp){
        "WhatsApp" -> packageName = "com.whatsapp"
        "Twitter" -> packageName = "com.twitter.android"
        "Facebook" -> packageName = "com.facebook.katana"
        "Instagram" -> packageName = "com.instagram.android"
        "Yahoo" -> packageName = "com.yahoo.mobile.client.android.mail"
        "Message" -> packageName = "com.google.android.apps.messaging"
        "WeChat" -> packageName = "com.tencent.mm"
        "TikTok" -> packageName = "com.zhiliaoapp.musically"
        else -> null
    }

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "text")
        `package` = packageName
    }

    try{
        context.startActivity(sendIntent)
    } catch (e: ActivityNotFoundException){
        e.printStackTrace()
    }
}
