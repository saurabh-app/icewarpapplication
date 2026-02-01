package com.example.icewarpapplication.ui.channels

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelsScreen(
    viewModel: ChannelsViewModel,
    onBack: () -> Unit
) {
    val channels by viewModel.channels.collectAsState()

    val grouped = channels.groupBy {
        it.groupFolderName ?: "Others"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Channels") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {

            grouped.forEach { (groupName, channelList) ->

                item {
                    Text(
                        text = groupName,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                items(channelList) { channel ->
                    Text(
                        text = channel.name,
                        modifier = Modifier.padding(start = 32.dp, bottom = 12.dp)
                    )
                }
            }
        }
    }
}