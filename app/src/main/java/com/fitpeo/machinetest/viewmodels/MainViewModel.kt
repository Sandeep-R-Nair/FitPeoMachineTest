package com.fitpeo.machinetest.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitpeo.machinetest.apis.ApiServices
import com.fitpeo.machinetest.models.PhotosResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Sandeep on 27-Feb-23.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val apiService: ApiServices) : ViewModel() {
    private val photosList: MutableLiveData<PhotosResponse?> = MutableLiveData()

    fun getPhotosListObserver(): MutableLiveData<PhotosResponse?> {
        return photosList
    }

    fun getPhotoList() {
        val call: Call<PhotosResponse> = apiService.getPhotosList()
        call!!.enqueue(object : Callback<PhotosResponse?> {
            override fun onResponse(
                call: Call<PhotosResponse?>,
                response: Response<PhotosResponse?>
            ) {
                photosList.postValue(response.body())
            }

            override fun onFailure(call: Call<PhotosResponse?>, t: Throwable) {
                photosList.postValue(null)
                Log.e("Error :", t.message.toString())
            }
        })
    }
}