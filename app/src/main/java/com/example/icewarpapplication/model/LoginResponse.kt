package com.example.icewarpapplication.model

data class LoginResponse(
//    @SerializedName("token")
//    val token: String

    val authorized: Boolean,
    val token: String,
    val host: String,
    val email: String,
    val ok: Boolean,
)
