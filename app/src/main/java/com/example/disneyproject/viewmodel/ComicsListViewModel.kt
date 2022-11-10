package com.example.disneyproject.viewmodel

import android.os.Build
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.disneyproject.services.ComicsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.sql.Timestamp
import java.util.*

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
        val hash = generateHash(ts, prk, pbk)
        val comic = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ComicsService::class.java)
            .getComicById(24112, pbk, ts, hash) // hardcoding the comic ID for now
        emit(comic)
    }

    /**
     * Generates the hash needed to successfully authenticate with the marvel service
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun generateHash(ts: String, privateKey: String, publicKey: String): String {
        val digest = MessageDigest.getInstance("MD5")
        val dprk = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String(Base64.getDecoder().decode(privateKey), Charsets.UTF_8)
        } else null
        val combinedKey = "$ts$dprk$publicKey"
        digest.update(combinedKey.toByteArray(), 0, combinedKey.length)
        return java.math.BigInteger(1, digest.digest()).toString(16)
    }

    companion object {
        const val pbk = "a1383a23f9e8cc9de8967db0515a0b6e"
        const val prk = "NjAwMmY2NTMyNDlkYjA4NTU1ZDdjMDU2ZjNkZGU0MDdiMjg3NDRjNA=="
    }
}