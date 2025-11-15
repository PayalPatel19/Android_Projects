package com.example.userdetails.data.api


import com.example.userdetails.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUsers(): Call<UserResponse>
}
