package com.example.movieapp.MyList.logic

import androidx.lifecycle.ViewModel
import com.example.movieapp.Home.data.room.AnimeItem
import com.example.movieapp.R

class ListViewModel: ViewModel() {
    val exampleList = arrayListOf(
        AnimeItem(1, "attack", R.drawable.home_attackontitan, 9.2),
        AnimeItem(2, "Naruto", R.drawable.home_naruto, 9.3),
        AnimeItem(3, "Dragon Ball", R.drawable.home_attackontitan, 9.5),
        AnimeItem(4, "Death Note", R.drawable.home_naruto, 9.1),
        AnimeItem(5, "One Piece", R.drawable.home_attackontitan, 9.4),
        AnimeItem(6, "One Piece", R.drawable.home_attackontitan, 9.4),
    )
}