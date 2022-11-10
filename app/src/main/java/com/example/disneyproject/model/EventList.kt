package com.example.disneyproject.model

data class EventList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: List<EventSummary>?
)
