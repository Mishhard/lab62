package com.example.myapplicationlab6

data class LoginResponse(
    val token: String // JWT-токен, возвращаемый сервером
)

data class UserResponse(
    val id: String,
    val name: String,
    val email: String
)

data class RegisterRequest(
    val login: String,
    val password: String,
    val email: String
)

data class RegisterResponse(
    val success: Boolean,
    val message: String
)
