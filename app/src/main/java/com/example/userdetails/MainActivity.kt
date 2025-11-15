package com.example.userdetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
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

        binding.progressBar.visibility = View.VISIBLE

        viewModel.users.observe(this) { list ->
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.adapter = UserAdapter(list) { user -> openDetails(user) }
        }

        viewModel.error.observe(this) { message ->
            binding.progressBar.visibility = View.GONE
            showErrorDialog(message)
        }

        viewModel.fetchUsers()
    }

    private fun openDetails(user: com.example.userdetails.data.model.User) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("Retry") { _, _ ->
                binding.progressBar.visibility = View.VISIBLE
                viewModel.fetchUsers()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
