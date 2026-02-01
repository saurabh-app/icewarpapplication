package com.example.icewarpapplication.data.repository

import com.example.icewarpapplication.data.api.AuthApi
import com.example.icewarpapplication.data.db.AppDatabase
import com.example.icewarpapplication.model.LoginResponse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(
    private val api: AuthApi,
    private val database: AppDatabase
) {

    fun login(username: String, password: String): Single<LoginResponse> {
        return api.login(username, password)
    }

    suspend fun saveToken(token: String) {
        withContext(Dispatchers.IO) {
            database.database.authTokenQueries.insertToken(token)
        }
    }
}
