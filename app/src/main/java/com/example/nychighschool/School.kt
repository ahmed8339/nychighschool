package com.example.nychighschool

data class School (
    val school_name : String,
    val dbn: String,
    val overview_paragraph: String
)

data class SchoolDetail(
    val school: School
)