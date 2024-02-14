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

    val highSchools = listOf(School("Clinton School Writers & Artists, M.S. 260", "260","Students who are prepared for college must have an education that encourages them to take risks as they produce and perform. Our college preparatory curriculum develops writers and has built a tight-knit community. Our school develops students who can think analytically and write creatively. Our arts programming builds on our 25 years of experience in visual, performing arts and music on a middle school level. We partner with New Audience and the Whitney Museum as cultural partners. We are a International Baccalaureate (IB) candidate school that offers opportunities to take college courses at neighboring universities."),
        School("Liberation Diploma Plus High School", "261", "oVERvIEW"))


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
}

