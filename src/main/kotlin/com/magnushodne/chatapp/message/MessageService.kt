package com.magnushodne.chatapp.message

import com.magnushodne.chatapp.channel.ChannelService
import com.magnushodne.chatapp.dtos.NewMessageDto
import com.magnushodne.chatapp.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MessageService(
    @Autowired private val messageRepository: MessageRepository,
    @Autowired private val userService: UserService,
    @Autowired private val channelService: ChannelService
) {

    fun createMessage(message: NewMessageDto): MessageEntity {
        val messageEntity = MessageEntity(
            content = message.content
        )
        messageEntity.author = userService.getUserById(message.authorId)
        messageEntity.channel = channelService.getChannelById(message.channelId)
        return messageRepository.save(messageEntity)
    }

    fun getMessageById(id: Long): MessageEntity? {
        return messageRepository.findByIdOrNull(id)
    }

    fun getAllMessages(): List<MessageEntity> {
        return messageRepository.findAll()
    }

    fun updateMessage(message: MessageEntity): MessageEntity? {
        message.id?.let {
            return messageRepository.save(message)
        }
        return null
    }

    fun deleteOneMessage(id: Long): Boolean {
        val message = messageRepository.findByIdOrNull(id)
        message?.let { messageRepository.delete(it)
            return true
        }?: return false
    }
}

