package com.example.movieapp.features.Details.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
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
import com.example.movieapp.core.network.models.shared.Genre
import com.example.movieapp.features.Details.data.BottomSheetContent

@Composable
fun TitleSection(
    title: String,
    onActionTriggered: (BottomSheetContent) -> Unit
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
        TitleSectionShare(onActionTriggered = {content -> onActionTriggered(content)})

    }
}

@Composable
fun TitleSectionShare(onActionTriggered: (BottomSheetContent) -> Unit) {
    IconButton(
        onClick = { onActionTriggered(BottomSheetContent.SHARE) }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.Send,
            contentDescription = "List"
        )
    }
}

@Composable
fun RatingYearGenres(
    animeItem: AnimeItemTopHits,
    onActionTriggered: (BottomSheetContent) -> Unit
) {
    Row(
        modifier = Modifier.padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Rating(onActionTriggered = { content -> onActionTriggered(content) }, animeItem)
        Text(
            text = if(animeItem.year == 0) "-" else animeItem.year.toString(),
            fontSize = 15.sp,
            modifier = Modifier.padding(end = 15.dp, start = 15.dp)
        )
        LazyRow {
            items(animeItem.genres) { genre ->
                GenreBox(name = genre.name)
            }
        }
    }
}

@Composable
fun Rating(onActionTriggered: (BottomSheetContent) -> Unit, animeItem: AnimeItemTopHits) {
    Icon(
        imageVector = Icons.Default.Star,
        contentDescription = "Star",
        tint = Color.Green,
        modifier = Modifier
            .padding(5.dp)
            .clickable { onActionTriggered(BottomSheetContent.RATING) }
    )
    Text(
        text = animeItem.rating.toString(),
        color = Color.Green,
        modifier = Modifier.padding(5.dp)
    )
}

@Composable
fun GenreBox(name: String){
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


@Composable
fun ButtonsSection(
    onNavigate: (String) -> Unit,
    onActionTriggered: (BottomSheetContent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayButton(Modifier.weight(1f)) { direction -> onNavigate(direction) }
        Spacer(modifier = Modifier.width(10.dp))
        DownloadButton(Modifier.weight(1f)) { content -> onActionTriggered(content) }
    }
}

@Composable
fun PlayButton(modifier: Modifier, onNavigate: (String) -> Unit) {
    FilledTonalButton(
        onClick = { onNavigate("videoPlayer") },
        modifier = modifier,
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
}

@Composable
fun DownloadButton(modifier: Modifier, onActionTriggered: (BottomSheetContent) -> Unit) {
    OutlinedButton(
        onClick = { onActionTriggered(BottomSheetContent.DOWNLOAD) },
        modifier = modifier,
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

@Composable
fun DescriptionSection(animeItem: AnimeItemTopHits) {
    var isExpanded by remember { mutableStateOf(false) }

    val (displayText, actionText) = when {
        !isExpanded && animeItem.description.length > 100 -> animeItem.description.take(110).trim() to "View More"
        !isExpanded -> animeItem.description to ""
        else -> animeItem.description to "View Less"
    }

    Column(Modifier.padding(20.dp)) {
        Text("Genre: ", style = MaterialTheme.typography.body1)
        GenreChips(genres = animeItem.genres)

        Spacer(Modifier.height(10.dp))

        AnnotatedClickableText(
            displayText = displayText,
            actionText = actionText,
            isExpanded = isExpanded,
            onToggleExpand = { isExpanded = !isExpanded }
        )
    }
}

@Composable
fun GenreChips(genres: List<Genre>) {
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        genres.forEach { genre ->
            Text(text = genre.name, modifier = Modifier.padding(end = 8.dp))
        }
    }
}

@Composable
fun AnnotatedClickableText(displayText: String, actionText: String, isExpanded: Boolean, onToggleExpand: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        append(displayText)
        if (actionText.isNotEmpty()) {
            pushStringAnnotation(tag = "ACTION", annotation = "action")
            withStyle(style = SpanStyle(color = Color.Green, fontWeight = FontWeight.Bold)) {
                append(" $actionText")
            }
            pop()
        }
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations("ACTION", offset, offset).firstOrNull()?.let {
                onToggleExpand()
            }
        },
        style = LocalTextStyle.current.copy(color = Color.Black)
    )
}

@Composable
fun MoreLikeThis(animeList: List<AnimeItemTopHits>) {
    LazyRow() {
        items(animeList.size){animeItem ->
            Card(
                modifier = Modifier
                    .size(150.dp, 250.dp)
                    .padding(5.dp)
            ){
                Box {
                    AsyncImage(
                        model = animeList[animeItem].image,
                        contentDescription = "animePhoto",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    MoreLikeThisRating(animeList[animeItem].rating)
                }
            }
        }
    }
}

@Composable
fun MoreLikeThisRating(rating: Double) {
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
                text = rating.toString(),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun Comments(onClick: (String) -> Unit) {
    var selected by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(15.dp)) {
        CommentHeader(){direction -> onClick(direction)}
        CommentContent("Jan Kowalski", selected, onSelect = { selected = !selected })
    }
}


@Composable
fun CommentHeader(onClick: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "29.5K Comments",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        TextButton(onClick = { onClick("nextScreen") }) {
            Text(text = "See all", color = Color.Green)
        }
    }
}

@Composable
fun CommentContent(username: String, selected: Boolean, onSelect: () -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserProfileAndName(username)
        IconButton(onClick = onSelect) {
            Icon(
                imageVector = if (selected) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                contentDescription = "Like",
                tint = if (selected) Color.Red else Color.Green
            )
        }
    }
    Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ut \" +\n" +
            "\"efficitur dolor. Lorem ipsum dolor sit amet, consectetur adipiscing elit")
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(end = 5.dp)
    ){
        Text(text = "4 days ago")
        TextButton(onClick = { }) {
            Text(text = "Reply")
        }
    }

}

@Composable
fun UserProfileAndName(username: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Card(
            modifier = Modifier.size(50.dp),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(R.drawable.detail_profile),
                contentDescription = "User Profile",
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = username, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
    }
}











