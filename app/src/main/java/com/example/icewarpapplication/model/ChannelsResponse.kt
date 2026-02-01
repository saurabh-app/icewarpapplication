package com.example.icewarpapplication.model

data class ChannelsResponse(
    val channels: List<Channel>,
    val ok: Boolean,
)

data class Channel(
    val id: String,
    val name: String,
    val created: Long,
    val creator: String,
    @JsonProperty("is_member")
    val isMember: Boolean,
    @JsonProperty("group_email")
    val groupEmail: String,
    @JsonProperty("group_folder_name")
    val groupFolderName: String,
    @JsonProperty("is_active")
    val isActive: Boolean,
    @JsonProperty("is_recent")
    val isRecent: Boolean,
    @JsonProperty("is_auto_followed")
    val isAutoFollowed: Boolean,
    @JsonProperty("is_notifications")
    val isNotifications: Boolean,
    @JsonProperty("last_seen")
    val lastSeen: String,
    val latest: Long,
    @JsonProperty("unread_count")
    val unreadCount: Long,
    @JsonProperty("thread_unread_count")
    val threadUnreadCount: Long,
    val members: List<Any?>,
)

annotation class JsonProperty(val value: String)

