package com.fitpeo.machinetest.apis

import com.fitpeo.machinetest.models.PhotosResponse
import com.fitpeo.machinetest.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Sandeep on 27-Feb-23.
 */
interface ApiServices {
    @GET(Constants.END_POINT)
    fun getPhotosList(): Call<PhotosResponse>
}