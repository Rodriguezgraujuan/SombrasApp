package com.example.sombras.data.service

import com.example.sombras.data.model.UpdateProfileRequest
import com.example.sombras.data.model.UserProfileResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UsuarioApi {
    @GET("/api/auth/users/{id}")
    fun getProfile(@Path("id") id: Long): Call<UserProfileResponse>

    @PUT("/api/auth/users/{id}")
    fun updateProfile(
        @Path("id") id: Long,
        @Body request: UpdateProfileRequest
    ): Call<Void>

}