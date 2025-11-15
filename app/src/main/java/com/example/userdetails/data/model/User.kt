package com.example.userdetails.data.model

import java.io.Serializable

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val email: String,
    val phone: String,
    val image: String
) : Serializable