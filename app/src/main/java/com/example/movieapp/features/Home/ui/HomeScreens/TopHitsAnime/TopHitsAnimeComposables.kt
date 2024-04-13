package com.example.movieapp.features.Home.ui.HomeScreens.TopHitsAnime

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.core.database.entities.AnimeItemMyList
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.shared.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TopHitsListImage(item: AnimeItemTopHits, modifier: Modifier) {
    Box(
        modifier = modifier
            .height(200.dp)
            .padding(10.dp)
    ) {
        ListImagePhoto(item.image)
        ListImageRating(item.rating)
    }
}

@Composable
fun ListImagePhoto(image: String) {
    Card() {
        AsyncImage(
            model = image,
            contentDescription = "itemPhoto",
            alpha = 0.85f,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun ListImageRating(rating: Double) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp)
            .size(30.dp, 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Green,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(corner = CornerSize(5.dp))
    ) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                rating.toString(),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun TopHitsListDetails(
    item: AnimeItemTopHits,
    sharedViewModel: SharedViewModel,
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(start = 15.dp, end = 25.dp)
    ) {
        ListDetailsTexts(item)
        ListDetailsButton(item, sharedViewModel)
    }
}

@Composable
fun ListDetailsTexts(item: AnimeItemTopHits) {
    Text(
        text = item.name,
        modifier = Modifier
            .width(200.dp),
        overflow = TextOverflow.Ellipsis,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = if(item.year > 0) "${item.year} | Japan" else "Japan")
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Genre: ${item.genres.joinToString { it.name }}",
        overflow = TextOverflow.Ellipsis,
        maxLines = 2
    )
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
fun ListDetailsButton(item: AnimeItemTopHits, sharedViewModel: SharedViewModel) {
    val currentItem = AnimeItemMyList(
        id = 0, name = item.name, image = item.image, rating = item.rating
    )
    var isCheck by remember { mutableStateOf(false) }
    var itemToDelete = AnimeItemMyList(0, "", "", 0.0)

    LaunchedEffect(currentItem) {
        isCheck = sharedViewModel.searchItemMyList(item.name).isNotEmpty()
    }

    FilledTonalButton(
        onClick = {
            if (!isCheck) {
                sharedViewModel.insertItemMyList(currentItem)
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    itemToDelete = sharedViewModel.searchItemMyList(item.name).first()
                    sharedViewModel.deleteItemMyList(itemToDelete)
                }
            }
            isCheck = !isCheck
        },
        modifier = Modifier
            .size(125.dp, 40.dp),
        border = BorderStroke(if(!isCheck) 2.dp else 2.dp, Color.Green),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if(!isCheck) Color.Green else Color.Transparent,
            contentColor = if(!isCheck) Color.White else Color.Green
        ),
        contentPadding = PaddingValues(5.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "")
        Text(
            text = "My List",
            modifier = Modifier
                .padding(start = 3.dp),
            fontSize = 15.sp)
    }
}









