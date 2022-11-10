package com.example.disneyproject.model

data class CharacterList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: List<CharacterSummary>?
)
