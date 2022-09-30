package com.magnushodne.chatapp.controller

import jdk.jshell.spi.ExecutionControl.NotImplementedException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/api/v1/login")
class LoginController {

    @GetMapping("/")
    fun getLoginInfo(): ResponseEntity.BodyBuilder {
        throw NotImplementedException("Not yet implemented")
    }

    @GetMapping("/config")
    fun getOpenIDConfig(): ResponseEntity.BodyBuilder {
        throw NotImplementedException("Not yet implemented")
    }

    @PostMapping("/")
    fun loginUser() {
        throw NotImplementedException("Not yet implemented")
    }

    @DeleteMapping("/")
    fun logoutUser() {
        throw NotImplementedException("Not yet implemented")
    }
}