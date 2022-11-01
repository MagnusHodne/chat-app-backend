package com.magnushodne.chatapp.channel

import com.magnushodne.chatapp.dtos.NewChannelDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI


@RestController
@RequestMapping("/api/v1/channels")
class ChannelController(@Autowired private val channelService: ChannelService) {

    @PostMapping
    fun createChannel(@RequestBody channel: NewChannelDto): ResponseEntity<ChannelEntity> {
        val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/channels").toUriString())
        return ResponseEntity.created(uri).body(channelService.createChannel(channel))
    }
}