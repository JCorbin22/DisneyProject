package com.example.disneyproject

import com.example.disneyproject.viewmodel.ComicsListViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ComicsListViewModelTest {
    private val viewModel = ComicsListViewModel()

    @Test
    fun `given a timestamp and api keys when the hash is generated it is correct`() {
        val hash = viewModel.generateHash("1", "abcd", "1234")
        assertEquals(hash, "ffd275c5130566a2916217b101f26150")
    }
}