package com.example.movieapp.features.Details.composables

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun titleSection(
    scaffoldState: BottomSheetScaffoldState,
    whichState: MutableState<String>,
    scope: CoroutineScope,
    title: String
) {
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = title,
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun belowTitleSection(
    scaffoldState: BottomSheetScaffoldState,
    whichState: MutableState<String>,
    scope: CoroutineScope,
    animeItem: AnimeItemTopHits
) {
    Row(
        modifier = Modifier.padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Star",
            tint = Color.Green,
            modifier = Modifier
                .padding(5.dp)
                .clickable {
                    whichState.value = "rating"
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
        )
        Text(
            text = animeItem.rating.toString(),
            color = Color.Green,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = if(animeItem.year == 0) "-" else animeItem.year.toString(),
            fontSize = 15.sp,
            modifier = Modifier.padding(end = 15.dp, start = 15.dp)
        )
       for(i in 0..animeItem.genres.size-1){
           genreBox(name = animeItem.genres[i].name.toString())
       }
    }
}

@Composable
fun genreBox(name: String){
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
            Text(name, Modifier.padding(6.dp), maxLines = 1)
        }
    }
    Spacer(modifier = Modifier.width(10.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun buttonsSection(
    scaffoldState: BottomSheetScaffoldState,
    whichState: MutableState<String>,
    scope: CoroutineScope,
    animeItem: AnimeItemTopHits,
    onClick: (String, String) -> Unit
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledTonalButton(
            onClick = { onClick("videoPlayer", animeItem.trailer.url) },
            modifier = Modifier
                .weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(5.dp)
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "",
                tint = Color.White
            )
            Text(
                text = "Play",
                modifier = Modifier
                    .padding(start = 3.dp),
                fontSize = 15.sp,
                color = Color.White
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
            Icon(
                painter = painterResource(R.drawable.profile_download),
                contentDescription = "",
                tint = Color.Green
            )
            Text("Download", color = Color.Green)
        }
    }
}

@Composable
fun DescriptionSection(animeItem: AnimeItemTopHits) {
    var isExpanded by remember { mutableStateOf(false) }

    val displayText = if (!isExpanded && animeItem.description.length > 100) {
        "${animeItem.description.take(110).trim()}... "
    } else if (!isExpanded) {
        animeItem.description
    } else {
        "${animeItem.description} View Less"
    }

    val annotatedString = buildAnnotatedString {
        append(displayText)
        if (animeItem.description.length > 100 || isExpanded) {
            pushStringAnnotation(tag = "ACTION", annotation = "action")
            withStyle(style = SpanStyle(color = Color.Green, fontWeight = FontWeight.Bold)) {
                append(if (isExpanded) "View Less" else "View More")
            }
            pop()
        }
    }

    Column(Modifier.padding(20.dp)) {
        Row {
            Text("Genre: ")
            animeItem.genres.forEach {
                Text(it.name)
                Spacer(Modifier.width(5.dp))
            }
        }
        Spacer(Modifier.height(10.dp))

        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                val annotation = annotatedString.getStringAnnotations("ACTION", offset, offset).firstOrNull()
                if (annotation != null) {
                    isExpanded = !isExpanded
                }
            },
            style = LocalTextStyle.current.copy(color = Color.Black)
        )
    }
}

@Composable
fun MoreLikeThis(animeList: List<AnimeItemTopHits>) {
    LazyRow() {
        items(animeList.size){item ->
            Card(
                modifier = Modifier
                    .size(150.dp, 250.dp)
                    .padding(5.dp)
            ){
                Box(){
                    AsyncImage(
                        model = animeList[item].image,
                        contentDescription = "animePhoto",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
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
                            Text(
                                text = animeList[item].rating.toString(),
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
fun Comments(onClick: (String) -> Unit) {
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
        TextButton(onClick = { onClick("nextScreen") }) {
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
                        painter = painterResource(R.drawable.detail_profile),
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