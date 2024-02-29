package com.example.movieapp.Details.ui.Details

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.Details.logic.DetailsViewModel
import com.example.movieapp.R

@Composable
fun sheetToDisplay(viewModel: DetailsViewModel){
    val context = LocalContext.current

    val icons = mutableListOf(
        R.drawable.whatsapp,
        R.drawable.twitter,
        R.drawable.facebook,
        R.drawable.instagram,
        R.drawable.yahoo,
        R.drawable.message,
        R.drawable.wechat,
        R.drawable.tiktok,
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

    Column(
        Modifier
            .height(350.dp)
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Share to",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(15.dp)
        )
        Divider(thickness = 1.dp, color = Color.LightGray)

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for(i in 0..3){
                Column {
                    Image(
                        painterResource(id = icons[i]),
                        contentDescription = "",
                        modifier = Modifier
                            .size(75.dp)
                            .padding(15.dp)
                            .clickable {
                                intent(context, names[i])
                            }
                    )
                    Text(
                        text = names[i],
                        fontSize = 13.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for(i in 4..6){
                Column() {
                    Image(
                        painterResource(id = icons[i]),
                        contentDescription = "",
                        modifier = Modifier
                            .size(75.dp)
                            .padding(15.dp)
                            .clickable {
                                intent(context, names[i])
                            }
                    )
                    Text(
                        text = names[i],
                        fontSize = 13.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

fun intent(context: Context, whatApp: String){
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
