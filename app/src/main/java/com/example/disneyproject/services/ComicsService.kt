package com.example.disneyproject.services

import com.example.disneyproject.model.ComicDataWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicsService {

    /**
     * Gets the details of a specific comic from the provided ID.
     */
    @GET("/v1/public/comics/{comicId}")
    suspend fun getComicById(
        @Path("comicId") comicId: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): ComicDataWrapper

}