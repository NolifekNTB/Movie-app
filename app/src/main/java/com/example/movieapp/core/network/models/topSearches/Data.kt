package com.example.movieapp.core.network.models.topSearches

data class DataTopSearches(
    val entry: Entry,
    val episodes: List<Episode>,
    val region_locked: Boolean
)