package com.magnushodne.chatapp.channel

import com.magnushodne.chatapp.dtos.NewChannelDto
import com.magnushodne.chatapp.server.ServerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ChannelService(@Autowired private val channelRepository: ChannelRepository, @Autowired private val serverService: ServerService) {

    fun createChannel(channel: NewChannelDto): ChannelEntity {
        val channelEntity = ChannelEntity(
            name = channel.name,
            description = channel.description
        )
        channelEntity.server = serverService.getServerById(channel.serverId)
        return channelRepository.save(channelEntity)
    }

    fun getChannelById(id: Long): ChannelEntity? {
        return channelRepository.findByIdOrNull(id)
    }

    fun getAllChannels(): List<ChannelEntity> {
        return channelRepository.findAll()
    }

    fun updateChannel(channel: ChannelEntity): ChannelEntity? {
        channel.id?.let {
            return channelRepository.save(channel)
        }
        return null
    }

    fun deleteOneChannel(id: Long): Boolean {
        val channel = channelRepository.findByIdOrNull(id)
        channel?.let {
            channelRepository.delete(it)
            return true
        } ?: return false
    }

}