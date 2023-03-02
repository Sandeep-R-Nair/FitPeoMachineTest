package com.fitpeo.machinetest.views

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fitpeo.machinetest.adapters.PhotosListAdapter
import com.fitpeo.machinetest.databinding.ActivityMainBinding
import com.fitpeo.machinetest.models.PhotosResponse
import com.fitpeo.machinetest.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding?= null
    private var photosList: PhotosResponse? = null
    private var mainViewModel: MainViewModel? = null
    private var adapter: PhotosListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.recyclerPhotos.layoutManager = LinearLayoutManager(this)
        binding!!.recyclerPhotos.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        adapter = PhotosListAdapter(this@MainActivity, photosList)
        binding!!.recyclerPhotos.adapter = adapter

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel!!.getPhotoList()

        mainViewModel!!.getPhotosListObserver().observe(this) { photosModels ->
            binding!!.progressBar.visibility = View.GONE
            if (photosModels != null) {
                photosList = photosModels
                adapter!!.updatePhotosList(photosModels)
                binding!!.textNoRecord.visibility = View.GONE
            }
            if (photosModels == null) {
                binding!!.recyclerPhotos.visibility = View.GONE
                binding!!.textNoRecord.visibility = View.VISIBLE
            }
        }
    }
}