package com.magnushodne.chatapp.server

import com.magnushodne.chatapp.dtos.NewServerDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api/v1/servers")
class ServerController(@Autowired private val serverService: ServerService) {

    @PostMapping
    fun createServer(@RequestBody server: NewServerDto): ResponseEntity<ServerEntity> {
        val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/servers").toUriString())
        return ResponseEntity.created(uri).body(serverService.createServer(server))
    }

    @GetMapping("/{id}")
    fun getOneServer(@PathVariable id: Long): ResponseEntity<ServerEntity?> {
        return ResponseEntity.ok(serverService.getServerById(id))
    }

    @GetMapping
    fun getAllServers(): ResponseEntity<List<ServerEntity>>{
        return ResponseEntity.ok(serverService.getAllServers())
    }
}