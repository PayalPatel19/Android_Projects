package com.example.userdetails

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.userdetails.data.model.User
import com.example.userdetails.databinding.ActivityMainBinding
import com.example.userdetails.ui.UserAdapter
import com.example.userdetails.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        viewModel.users.observe(this) { list ->
            binding.recyclerView.adapter = UserAdapter(list) { user ->
                openDetails(user)
            }
        }

        viewModel.fetchUsers()
    }

    private fun openDetails(user: User) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}