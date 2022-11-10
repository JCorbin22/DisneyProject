package com.example.disneyproject.model

data class ComicDataWrapper(
    var code: Int?,
    var status: String?,
    var copyright: String?,
    var attributionText: String?,
    var attributionHTML: String?,
    var data: ComicDataContainer?,
    var etag: String?
)
