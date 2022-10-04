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

    @GetMapping("/")
    fun getAllChats(): ResponseEntity<List<Message>>{
        return ResponseEntity.ok().body(ArrayList())
    }

    @DeleteMapping("/")
    fun deleteOne(): ResponseEntity.BodyBuilder {
        throw NotImplementedException("Not yet implemented")
    }
}