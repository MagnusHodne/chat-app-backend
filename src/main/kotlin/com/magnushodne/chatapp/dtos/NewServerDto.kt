package com.magnushodne.chatapp.dtos

data class NewServerDto(
    val name: String,
    val description: String,
    val ownerId: Long
)
