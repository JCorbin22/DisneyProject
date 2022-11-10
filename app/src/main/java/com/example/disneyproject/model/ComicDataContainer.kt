package com.example.disneyproject.model

data class ComicDataContainer(
    var offset: Int?,
    var limit: Int?,
    var total: Int?,
    var count: Int?,
    var results: List<Comic>?
)
