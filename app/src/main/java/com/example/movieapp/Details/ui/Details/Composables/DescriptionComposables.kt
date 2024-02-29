package com.example.movieapp.Details.ui.Details.Composables

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun titleSection(
    scaffoldState: BottomSheetScaffoldState,
    whichState: MutableState<String>,
    scope: CoroutineScope
) {
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
}

@Composable
fun belowTitleSection(){
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun buttonsSection(
    scaffoldState: BottomSheetScaffoldState,
    whichState: MutableState<String>,
    scope: CoroutineScope, ) {
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
                painter = painterResource(R.drawable.download),
                contentDescription = "",
                tint = Color.Green
            )
            Text("Download", color = Color.Green)
        }
    }
}

@Composable
fun descriptionSection(){
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