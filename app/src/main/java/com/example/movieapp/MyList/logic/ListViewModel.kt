package com.example.movieapp.MyList.logic

import androidx.lifecycle.ViewModel
import com.example.movieapp.Home.data.room.AnimeItemTopHits

class ListViewModel: ViewModel() {
    val image = "https://static.wikia.nocookie.net/naruto/images/d/d6/Naruto_Part_I.png/revision/latest/scale-to-width-down/1200?cb=20210223094656"

    val exampleList = arrayListOf(
        AnimeItemTopHits(1, "attack", image, 9.2),
        AnimeItemTopHits(2, "Naruto", image, 9.3),
        AnimeItemTopHits(3, "Dragon Ball", image, 9.5),
        AnimeItemTopHits(4, "Death Note", image, 9.1),
        AnimeItemTopHits(5, "One Piece", image, 9.4),
        AnimeItemTopHits(6, "One Piece", image, 9.4),
    )
}