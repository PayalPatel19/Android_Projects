package com.example.userdetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userdetails.data.model.User
import com.example.userdetails.data.model.UserResponse
import com.example.userdetails.data.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    val users = MutableLiveData<List<User>>()
    val error = MutableLiveData<String>()

    fun fetchUsers() {
        repository.getUsers().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val list = response.body()!!.users
                    if (list.isEmpty()) {
                        error.value = "No users found!"
                    } else {
                        users.value = list
                    }
                } else {
                    error.value = "Failed to load data!"
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                error.value = "Network Error: ${t.localizedMessage}"
            }
        })
    }
}
