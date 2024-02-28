package com.example.movieapp.Details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R

@Preview
@Composable
fun CommentsScreenPreview() {
    CommentsScreen()
}

@Composable
fun CommentsScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            TopBarComments()
            CommentsList()
        }
        Column(Modifier.weight(0.15f)) {
            AddComment()
        }
    }
}

@Composable
fun TopBarComments() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = ""
        )
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = ""
        )
    }
}

@Composable
fun CommentsList() {
    //Follow comment
    var selected by remember { mutableStateOf(false) }

    repeat(5){
        Column(){
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 15.dp),
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
            Row(modifier = Modifier.padding(start = 15.dp)){
                Text(text = "\n" +
                        "\n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ut " +
                        "efficitur dolor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. ")
            }
            Row(
                Modifier
                    .width(250.dp)
                    .padding(start = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(onClick = { selected = !selected }) {
                    Icon(
                        imageVector = if (selected) Icons.Outlined.Favorite
                        else Icons.Filled.Favorite,
                        contentDescription = "",
                        tint = if(selected) Color.Black else Color.Green
                    )
                }
                Text(text = "125")
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "4 days ago")
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Reply")
                }
            }
        }
    }
}

@Composable
fun AddComment() {
    var comment by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf(false) }


    Row(
        modifier =Modifier.padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedTextField(
            value = comment,
            onValueChange = {comment = it; selected = !selected},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Green
            ),
            placeholder = {Text(text = "Add comment...")},
            trailingIcon = { Icon(imageVector = Icons.Default.Face, contentDescription = "")},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Box(contentAlignment = Alignment.Center){
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Green
                ),
                shape = CircleShape
                ){
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}












