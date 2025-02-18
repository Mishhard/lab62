package com.example.myapplicationlab6

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Авторизация через Query параметры
    @POST("/auth/login")
    fun login(
        @Query("login") login: String,
        @Query("password") password: String
    ): Call<LoginResponse>

    // Получение информации о пользователе через Path параметр
    @GET("/users/{guid}")
    fun getUser(@Path("guid") guid: String): Call<UserResponse>

    @POST("/auth/register")
    fun register(
        @Query("login") login: String,
        @Query("password") password: String,
        @Query("email") email: String
    ): Call<RegisterResponse>
}