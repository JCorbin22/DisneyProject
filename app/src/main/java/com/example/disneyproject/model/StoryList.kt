package com.example.disneyproject.model

data class StoryList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: List<StorySummary>?
)
