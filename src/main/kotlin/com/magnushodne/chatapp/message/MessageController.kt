package com.magnushodne.chatapp.message

import jdk.jshell.spi.ExecutionControl.NotImplementedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/chats")
class MessageController(@Autowired private val messageService: MessageService) {

    @PostMapping
    fun createMessage(@RequestBody message: MessageEntity): ResponseEntity<MessageEntity> {
        return ResponseEntity.ok(messageService.createMessage(message))
    }

    @GetMapping("/{id}")
    fun getOneMessage(@PathVariable id: Long): ResponseEntity<MessageEntity?> {
        return ResponseEntity.ok(messageService.getMessageById(id))
    }

    @GetMapping
    fun getAllChats(): ResponseEntity<List<MessageEntity>>{
        return ResponseEntity.ok(messageService.getAllMessages())
    }

    @PutMapping("/{id}")
    fun updateMessage(@PathVariable id: Long, @RequestBody message: MessageEntity): ResponseEntity<MessageEntity?> {
        return ResponseEntity.ok(messageService.updateMessage(message))
    }

    @DeleteMapping("/{id}")
    fun deleteOneMessage(@PathVariable id: Long): ResponseEntity<Boolean> {
        return ResponseEntity.ok(messageService.deleteOneMessage(id))
    }

}