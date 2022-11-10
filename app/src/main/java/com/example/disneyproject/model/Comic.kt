package com.example.disneyproject.model

import java.util.Date

data class Comic(
    var name: String? = null,
    var tagLine: String? = null,
    var rating: Double? = null,
    var date: String? = null,
    var id: Int? = null,
    var digitalId: Int? = null,
    var title: String? = null,
    var issueNumber: Double? = null,
    var variantDescription: String? = null,
    var description: String? = null,
    // var modified: Date? = null,
    var isbn: String? = null,
    var upc: String? = null,
    var diamondCode: String? = null,
    var ean: String? = null,
    var issn: String? = null,
    var format: String? = null,
    var pageCount: Int? = null,
    var textObjects: List<TextObject>? = null,
    var resourceURI: String? = null,
    var urls: List<Url>? = null,
    var series: SeriesSummary? = null,
    var variants: List<ComicSummary>? = null,
    var collections: List<ComicSummary>? = null,
    var collectedIssues: List<ComicSummary>? = null,
    var dates: List<ComicDate>? = null,
    var prices: List<ComicPrice>? = null,
    var thumbnail: Image? = null,
    var images: List<Image>? = null,
    var creators: CreatorList? = null,
    var characters: CharacterList? = null,
    var stories: StoryList? = null,
    var events: EventList? = null
)
