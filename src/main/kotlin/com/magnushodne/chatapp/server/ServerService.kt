package com.magnushodne.chatapp.server

import com.magnushodne.chatapp.dtos.NewServerDto
import com.magnushodne.chatapp.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServerService(
    @Autowired private val serverRepository: ServerRepository,
    @Autowired private val userService: UserService
    ) {

    fun createServer(server: NewServerDto): ServerEntity {
        val serverEntity = ServerEntity(
            name = server.name,
            description = server.description
        )
        serverEntity.owner = userService.getUserById(server.ownerId)
        return serverRepository.save(serverEntity)
    }

    fun getServerById(id: Long): ServerEntity? {
        return serverRepository.findById(id).orElse(null)
    }

    fun getAllServers(): List<ServerEntity> {
        return serverRepository.findAll()
    }
}