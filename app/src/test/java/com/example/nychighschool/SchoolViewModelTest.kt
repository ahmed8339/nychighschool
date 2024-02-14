package com.example.nychighschool

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SchoolViewModelTest {
    @Test
    fun test() = runBlocking {
        val apiService = mock(SchoolApiService::class.java)
        //`when`(apiService.getSchools()).thenReturn(listOf(mockSchool()))

        val viewModel = SchoolViewModel(apiService)
        //assertEquals(viewModel.schools.value, listOf(mockSchool))
    }
}