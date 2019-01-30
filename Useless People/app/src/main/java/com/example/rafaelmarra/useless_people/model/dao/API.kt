package com.example.rafaelmarra.useless_people.model.dao

import com.example.rafaelmarra.useless_people.model.user.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<User>
}