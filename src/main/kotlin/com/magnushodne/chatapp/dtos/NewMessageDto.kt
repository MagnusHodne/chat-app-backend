package com.magnushodne.chatapp.dtos

data class NewMessageDto(
    val content: String,
    val channelId: Long,
    val authorId: Long
)