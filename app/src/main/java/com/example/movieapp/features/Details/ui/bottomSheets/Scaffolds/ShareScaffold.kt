package com.example.movieapp.features.Details.ui.bottomSheets.Scaffolds

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
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
import com.example.movieapp.features.Details.data.ShareItem
import com.example.movieapp.features.Details.ui.bottomSheets.ScaffoldTitle

val shareItems = listOf(
    ShareItem(R.drawable.share_whatsapp, "WhatsApp", "com.whatsapp"),
    ShareItem(R.drawable.share_twitter, "Twitter", "com.twitter.android"),
    ShareItem(R.drawable.share_facebook, "Facebook", "com.facebook.katana"),
    ShareItem(R.drawable.share_instagram, "Instagram", "com.instagram.android"),
    ShareItem(R.drawable.share_yahoo, "Yahoo", "com.yahoo.mobile.client.android.mail"),
    ShareItem(R.drawable.share_message, "Message", "com.google.android.apps.messaging"),
    ShareItem(R.drawable.share_wechat, "WeChat", "com.tencent.mm"),
    ShareItem(R.drawable.share_tiktok, "TikTok", "com.zhiliaoapp.musically")
)

@Composable
fun ShareDisplayBox(){
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .height(350.dp)
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScaffoldTitle(title = "Share to")
        Divider(thickness = 1.dp, color = Color.LightGray)
        ShareElements(context)
    }
}

@Composable
fun ShareElements(context: Context){
    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            shareItems.take(4).forEach { item ->
                ShareElementItem(item, context)
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            shareItems.drop(4).forEach { item ->
                ShareElementItem(item, context)
            }
        }
    }
}

@Composable
fun ShareElementItem(shareItem: ShareItem, context: Context){
    Column {
        Image(
            painterResource(id = shareItem.icon),
            contentDescription = "${shareItem.name} icon",
            modifier = Modifier
                .size(75.dp)
                .padding(15.dp)
                .clickable {
                    intentShare(context, shareItem.packageName)
                }
        )
        Text(
            text = shareItem.name,
            fontSize = 13.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

fun intentShare(context: Context, packageName: String){
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "Check this out!")
        `package` = packageName
    }

    try{
        context.startActivity(sendIntent)
    } catch (e: ActivityNotFoundException){
        Toast.makeText(context, "App not installed.", Toast.LENGTH_SHORT).show()
    }
}
