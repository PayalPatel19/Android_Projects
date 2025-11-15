package com.example.userdetails.data.repository

//import com.example.userdetails.data.api.RetrofitInstance
import com.example.userdetails.data.api.RetrofitInstance
import com.example.userdetails.data.model.UserResponse
import retrofit2.Call

class UserRepository {
    fun getUsers(): Call<UserResponse> = RetrofitInstance.api.getUsers()
}
