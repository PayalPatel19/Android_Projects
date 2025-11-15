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

        val user = intent.getSerializableExtra("user") as User

        binding.txtName.text = "${user.firstName} ${user.lastName}"
        binding.txtEmail.text = "Email: ${user.email}"
        binding.txtPhone.text = "Phone: ${user.phone}"
        binding.txtAge.text = "Age: ${user.age}"

        Glide.with(this)
            .load(user.image)
            .into(binding.imgUser)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}