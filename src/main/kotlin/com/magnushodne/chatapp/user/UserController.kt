package com.magnushodne.chatapp.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(@Autowired private val userService: UserService) {

    @PostMapping("/login")
    fun registerLogin(@RequestBody userInfo: UserInfoRequestBody): ResponseEntity<UserEntity> {
        return userService.registerLogin(userInfo)?.let { ResponseEntity.ok().body(it) } ?: ResponseEntity.badRequest().build()
    }
}

data class UserInfoRequestBody(val sub: String, val name: String, val picture: String)

