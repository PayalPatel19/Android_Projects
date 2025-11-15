package com.example.userdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.userdetails.data.model.User
import com.example.userdetails.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set Toolbar as ActionBar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Back arrow click
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Get user data
        val user = intent.getParcelableExtra<User>("user") ?: return

        binding.tvName.text = "${user.firstName} ${user.lastName}"
        binding.tvUsername.text = user.username
        binding.tvEmail.text = user.email
        binding.tvPhone.text = user.phone

        Glide.with(this)
            .load(user.image)
            .circleCrop()
            .placeholder(R.drawable.ic_user_placeholder)
            .into(binding.imgUser)
    }
}
