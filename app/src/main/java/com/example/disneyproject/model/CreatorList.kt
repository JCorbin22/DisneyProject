package com.example.disneyproject.model

data class CreatorList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: List<CreatorSummary>?
)
