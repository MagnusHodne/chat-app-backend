package com.magnushodne.chatapp.channel

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChannelRepository: JpaRepository<ChannelEntity, Long> {
}