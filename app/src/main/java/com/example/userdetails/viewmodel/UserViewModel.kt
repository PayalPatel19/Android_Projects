package com.example.userdetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userdetails.data.api.RetrofitInstance
import com.example.userdetails.data.model.User
import com.example.userdetails.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    val users = MutableLiveData<List<User>>()

    fun fetchUsers() {
        RetrofitInstance.api.getUsers()
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    users.value = response.body()?.users ?: emptyList()
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    users.value = emptyList()
                }
            })
    }
}