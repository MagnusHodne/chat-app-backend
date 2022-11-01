package com.magnushodne.chatapp.server

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServerRepository: JpaRepository<ServerEntity, Long> {
}