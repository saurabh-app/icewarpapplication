package com.example.icewarpapplication.ui.channels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.icewarpapplication.data.api.ChannelsApi
import com.example.icewarpapplication.data.api.RetrofitClient
import com.example.icewarpapplication.data.db.AppDatabase
import com.example.icewarpapplication.data.repository.ChannelsRepository

class ChannelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = ChannelsRepository(
            RetrofitClient.api.create(ChannelsApi::class.java),
            AppDatabase(this)
        )

        val viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ChannelsViewModel(repository) as T
                }
            }
        )[ChannelsViewModel::class.java]

        setContent {
            ChannelsScreen(
                viewModel = viewModel,
                onBack = { finish() }
            )
        }
    }
}
