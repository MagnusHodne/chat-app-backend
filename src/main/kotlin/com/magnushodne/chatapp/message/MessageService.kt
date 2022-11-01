package com.magnushodne.chatapp.message

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MessageService(@Autowired private val messageRepository: MessageRepository) {

    fun createMessage(message: MessageEntity): MessageEntity {
        return messageRepository.save(message)
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

