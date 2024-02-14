package com.example.nychighschool

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SchoolApiService {
    @GET("s3k6-pzi2.json")
    suspend fun getSchools(): List<School>
}
class SchoolViewModel() : ViewModel() {

    private val _schools = MutableStateFlow<List<School>>(emptyList())
    val schools :StateFlow<List<School>> = _schools

    private val _selectedSchool = MutableStateFlow<School?>(null)
    val selectedSchool: StateFlow<School?> = _selectedSchool

    init {
       fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try{
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://data.cityofnewyork.us/resource/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val apiService = retrofit.create(SchoolApiService::class.java)
                    _schools.value = apiService.getSchools()
            } catch (e: Exception){

            }
        }
    }

    fun selectSchool(school: School) {
        _selectedSchool.value = school
    }
}

