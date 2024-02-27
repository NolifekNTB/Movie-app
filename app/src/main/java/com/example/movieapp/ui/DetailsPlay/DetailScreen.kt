@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movieapp.ui.DetailsPlay

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieapp.MainViewModel
import com.example.movieapp.R
import kotlinx.coroutines.launch

@Composable
fun sheetToDisplay(viewModel: MainViewModel){
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

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun DetailScreen(navController: NavController, viewModel: MainViewModel) {
    //Share to scaffold
    val scaffoldState = rememberBottomSheetScaffoldState()
    val whichState = remember {
        mutableStateOf("")
    }

    androidx.compose.material3.BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            if(whichState.value == "share"){
                sheetToDisplay(viewModel)
            } else {
                sheetToDisplayDownload()
            }
        },
        sheetPeekHeight = 0.dp,
        sheetContainerColor = Color.White
    ) {
        Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                MainPhoto()
                Description(scaffoldState, whichState)
                Episodes()
                MoreLikeThisComments(navController)
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun MainPhoto() {
    Box {
        justImage()
        justImageInformation()
    }

}

@Composable
fun justImage(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)) {
        Image(
            painter = painterResource(R.drawable.demon_slayer),
            contentDescription = "MainPhoto",
            contentScale = ContentScale.FillHeight)
    }
}

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun justImageInformation() {
    Row(
         horizontalArrangement = Arrangement.SpaceBetween,
         modifier = Modifier
             .fillMaxWidth()
             .padding(top = 30.dp, start = 20.dp, end = 20.dp)
         ){
         Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
             contentDescription = "ArrowBack",
             tint = Color.White)
        Icon(
            painter = painterResource(R.drawable.mirror),
            contentDescription = "ArrowBack",
            tint = Color.White,
            modifier = Modifier.size(20.dp, 30.dp))
     }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Description(scaffoldState: BottomSheetScaffoldState, whichState: MutableState<String>) {
    //To intent share
    val scope = rememberCoroutineScope()

    Column() {
        //Title
        Row(
            modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Demon Slayer: (Kimetsu no Yaiba)",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.width(250.dp)
                )
            Spacer(modifier = Modifier.width(40.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.List,
                contentDescription = "List"
            )
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(
                onClick = {
                    whichState.value = "share"
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Send,
                    contentDescription = "List"
                )
            }
        }
        //Below tittle
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Star",
                tint = Color.Green,
                modifier = Modifier.padding(5.dp)
                )
            Text(
                text = "9.8",
                color = Color.Green,
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = "2022",
                fontSize = 15.sp,
                modifier = Modifier.padding(end = 15.dp, start = 15.dp)
            )
            Card(
                border = BorderStroke(2.dp, Color.Green),
                colors = CardDefaults.cardColors(
                    contentColor = Color.Green,
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(40.dp)
                ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("13+", Modifier.padding(5.dp))
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Card(
                border = BorderStroke(2.dp, Color.Green),
                colors = CardDefaults.cardColors(
                    contentColor = Color.Green,
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(80.dp)
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Japan", Modifier.padding(6.dp))
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Card(
                border = BorderStroke(2.dp, Color.Green),
                colors = CardDefaults.cardColors(
                    contentColor = Color.Green,
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8 .dp),
                modifier = Modifier.width(80.dp)
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Subtitle", Modifier.padding(6.dp))
                }
            }
        }
        //Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledTonalButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(5.dp)
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = ""
                )
                androidx.compose.material3.Text(
                    text = "Play",
                    modifier = Modifier
                        .padding(start = 3.dp),
                    fontSize = 15.sp
                )
            }
            Spacer(
                modifier = Modifier.width(10.dp))
            OutlinedButton(
                onClick = {
                    whichState.value = "download"
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                             }
                          },
                modifier = Modifier
                    .weight(1f),
                border = BorderStroke(2.dp, Color.Green),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(5.dp)
            ) {
                androidx.compose.material3.Icon(
                    painter = painterResource(R.drawable.download),
                    contentDescription = "",
                    tint = Color.Green
                )
                androidx.compose.material3.Text("Download", color = Color.Green)
            }
        }
        //Other info
        Column(Modifier.padding(20.dp)) {
            Text("Genre: Action, Adventure, Sci-Fi, Fantasy, Shounen")
            Spacer(Modifier.height(10.dp))
            Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Fusce tristique ullamcorper orci id dignissim. Cras vestibulum " +
                    "tortor a arcu posuere tempus. In vulputate, nisl quis pharetra",
                overflow = TextOverflow.Ellipsis,
                maxLines = 3)
        }
    }
}

@Composable
fun Episodes() {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Season 1") }
    Column(Modifier.padding(20.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Episodes",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            // Dropdown menu
            Box(){
                TextButton(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.TopEnd)
                ) {
                    Text(selectedText, color = Color.Green)
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = Color.Green
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(onClick = {
                        selectedText = "Season 1"
                        expanded = false
                    }) {
                        Text("Season 1")
                    }
                    DropdownMenuItem(onClick = {
                        selectedText = "Season 2"
                        expanded = false
                    }) {
                        Text("Season 2")
                    }
                    DropdownMenuItem(onClick = {
                        selectedText = "Season 3"
                        expanded = false
                    }) {
                        Text("Season 3")
                    }
                }
            }
        }
        LazyRow(){
            items(5){item ->
                    Box(
                        modifier = Modifier
                            .size(133.dp, 125.dp)
                            .padding(end = 10.dp)
                    ){
                        Card(){
                            Image(
                                painter = painterResource(R.drawable.attackontitan),
                                contentDescription = "",
                                contentScale = ContentScale.FillWidth
                            )
                        }
                        Text(
                            text = "Episode $item",
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 10.dp, bottom = 10.dp)
                        )
                }
            }
        }
    }
}

@Composable
fun MoreLikeThisComments(navController: NavController) {
    var selectedSection by remember { mutableStateOf(0) }
    val sections = listOf("More Like This", "Comments")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row() {
            sections.forEachIndexed { index, title ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                        .clickable { selectedSection = index }
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = if (selectedSection == index) Color.Green else Color.LightGray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 4.dp)
                    )
                    if (selectedSection == index) {
                        Divider(
                            color = Color.Green,
                            thickness = 2.dp,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Content of the selected section
        when (selectedSection) {
            0 -> {
                MoreLikeThis()
            }
            1 -> {
                 Comments(navController)
            }
        }
    }
}

@Composable
fun MoreLikeThis() {
    LazyRow() {
        items(3){
            Card(
                modifier = Modifier
                    .size(150.dp, 250.dp)
                    .padding(5.dp)
            ){
                Box(){
                    Image(
                        painter = painterResource(R.drawable.attackontitan),
                        contentDescription = "mainPhoto",
                        contentScale = ContentScale.FillWidth
                    )
                    Card(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 10.dp)
                            .size(30.dp, 20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Green,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(corner = CornerSize(5.dp))
                    ){
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            androidx.compose.material3.Text(
                                text = "8.8",
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Comments(navController: NavController) {
    //Follow comment
    var selected by remember { mutableStateOf(false) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Text(
            text = "29.5K Comments",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
            )
        TextButton(onClick = { navController.navigate("comments") }) {
            Text(
                text = "See all",
                color = Color.Green
            )
        }
    }

    Column(){
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Card(
                    modifier = Modifier
                        .size(50.dp),
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Jan Kowalski",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = ""
            )
        }
        Row(){
            Text(text = "\n" +
                    "\n" +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ut " +
                    "efficitur dolor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. ")
        }
        Row(
            Modifier
                .width(225.dp)
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = { selected = !selected }) {
                Icon(
                    imageVector = if (selected) Icons.Outlined.Favorite
                    else Icons.Filled.Favorite,
                    contentDescription = "",
                    tint = if(selected) Color.Black else Color.Green
                )
            }
            Text(text = "125")
            Spacer(modifier = Modifier.width(25.dp))
            Text(text = "4 days ago")
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Reply")
            }
        }
    }
}














