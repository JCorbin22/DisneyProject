package com.example.disneyproject.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.disneyproject.services.ComicsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.sql.Timestamp

/**
 * View model to back the ComicslistActivity
 *
 * (Calling "List" as the idea was to get to the point where a list of comics was available
 * to the user, to select individual comics)
 */
class ComicsListViewModel: ViewModel() {

    /**
     * Get a live data of the comic list from the retrofit service.
     * Constructing the liveData this way allows us to asynchronously fetch and display the data
     * in the composable by observingAsState.
     */
    // TODO: handle service failures, more elegantly create retrofit instance (Dagger?)
    val comicList = liveData {
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        val hash = generateHash(ts, PRIVATE_KEY, API_KEY)
        val comic = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ComicsService::class.java)
            .getComicById(24112, API_KEY, ts, hash) // hardcoding the comic ID for now
        emit(comic)
    }

    /**
     * Generates the hash needed to successfully authenticate with the marvel service
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun generateHash(ts: String, privateKey: String, publicKey: String): String {
        val digest = MessageDigest.getInstance("MD5")
        val combinedKey = "$ts$privateKey$publicKey"
        digest.update(combinedKey.toByteArray(), 0, combinedKey.length)
        return java.math.BigInteger(1, digest.digest()).toString(16)
    }

    companion object {
        const val API_KEY = "a1383a23f9e8cc9de8967db0515a0b6e"
        const val PRIVATE_KEY = "6002f653249db08555d7c056f3dde407b28744c4"
    }
}