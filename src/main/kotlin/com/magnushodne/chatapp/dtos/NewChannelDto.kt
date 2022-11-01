package com.magnushodne.chatapp.dtos

data class NewChannelDto(
    val name: String,
    val description: String,
    val serverId: Long
)
