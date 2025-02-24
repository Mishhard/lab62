package com.example.myapplicationlab6

data class LoginRequest(
    val login: String,
    val password: String
)

data class RegisterRequest(
    val login: String,
    val name: String,
    val password: String
)

data class LoginResponse(
    val token: String? // API возвращает token, а не success
)

data class RegisterResponse(
    val token: String?
)

data class UserResponse(
    val id: String?,
    val name: String?
)
