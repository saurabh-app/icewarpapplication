package com.example.icewarpapplication.ui.channels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icewarpapplication.data.repository.ChannelsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChannelsViewModel(
    private val repository: ChannelsRepository
) : ViewModel() {

    val channels = repository.getChannels()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            repository.fetchAndSaveChannels()
        }
    }
}