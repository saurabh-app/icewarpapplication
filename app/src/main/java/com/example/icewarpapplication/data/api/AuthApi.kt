package com.example.icewarpapplication.data.api

import com.example.icewarpapplication.model.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("iwauthentication.login.plain")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<LoginResponse>
}


