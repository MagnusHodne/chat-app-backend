package com.magnushodne.chatapp.message

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageService(@Autowired private val messageRepository: MessageRepository) {

    fun getAllMessages(): List<MessageEntity> {
        return messageRepository.findAll()
    }
}

