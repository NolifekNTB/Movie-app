package com.example.movieapp.MyList.logic

import androidx.lifecycle.ViewModel
import com.example.movieapp.Home.data.room.AnimeItem
import com.example.movieapp.R

class ListViewModel: ViewModel() {
    val image = "https://static.wikia.nocookie.net/naruto/images/d/d6/Naruto_Part_I.png/revision/latest/scale-to-width-down/1200?cb=20210223094656"

    val exampleList = arrayListOf(
        AnimeItem(1, "attack", image, 9.2),
        AnimeItem(2, "Naruto", image, 9.3),
        AnimeItem(3, "Dragon Ball", image, 9.5),
        AnimeItem(4, "Death Note", image, 9.1),
        AnimeItem(5, "One Piece", image, 9.4),
        AnimeItem(6, "One Piece", image, 9.4),
    )
}