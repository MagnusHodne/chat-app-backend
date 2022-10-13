package com.magnushodne.chatapp.controller

import com.magnushodne.chatapp.model.Message
import jdk.jshell.spi.ExecutionControl.NotImplementedException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/chats")
class ChatController {

    @GetMapping
    fun getAllChats(): ResponseEntity<String>{
        return ResponseEntity.ok().body("This will be a list of messages")
    }

    @DeleteMapping
    fun deleteOne(): ResponseEntity.BodyBuilder {
        throw NotImplementedException("Not yet implemented")
    }
}