package com.magnushodne.chatapp.message

import com.magnushodne.chatapp.dtos.NewMessageDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api/v1/chats")
class MessageController(@Autowired private val messageService: MessageService) {

    @PostMapping
    fun createMessage(@RequestBody message: NewMessageDto): ResponseEntity<MessageEntity> {
        val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/chats").toUriString())
        return ResponseEntity.created(uri).body(messageService.createMessage(message))
    }

    @GetMapping("/{id}")
    fun getOneMessage(@PathVariable id: Long): ResponseEntity<MessageEntity?> {
        return ResponseEntity.ok(messageService.getMessageById(id))
    }

    @GetMapping
    fun getAllChats(): ResponseEntity<List<MessageEntity>>{
        return ResponseEntity.ok(messageService.getAllMessages())
    }

}