package com.fitpeo.machinetest.views

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.fitpeo.machinetest.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    var binding: ActivityDetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding!!.textTitle.text = intent.getStringExtra("title").toString()
        Picasso.get()
            .load(intent.getStringExtra("image").toString())
            .into(binding!!.imageView)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}