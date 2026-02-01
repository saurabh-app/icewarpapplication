package com.example.icewarpapplication.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.icewarpapplication.data.api.ChannelsApi
import com.example.icewarpapplication.data.db.AppDatabase
import com.example.icewarpapplication.data.db.ChannelEntity
import com.example.icewarpapplication.model.ChannelsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ChannelsRepository(
    private val api: ChannelsApi,
    private val database: AppDatabase
) {

    suspend fun fetchAndSaveChannels() {
        withContext(Dispatchers.IO) {
            try {
                val tokenResult = database.database
                    .authTokenQueries
                    .getToken()
                    .executeAsOneOrNull()

                val token = tokenResult?.token ?: return@withContext

                val response: ChannelsResponse = api.getChannels(token)

                database.database.channelQueries.transaction {
                    response.data.forEach { channelDto ->
                        database.database.channelQueries.insertChannel(
                            id = channelDto.id,
                            name = channelDto.name,
                            groupFolderName = channelDto.groupFolderName
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getChannels(): Flow<List<ChannelEntity>> {
        return database.database
            .channelQueries
            .getAllChannels()
            .asFlow()
            .mapToList(Dispatchers.IO)
    }
}
