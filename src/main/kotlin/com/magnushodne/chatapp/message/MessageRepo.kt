package com.magnushodne.chatapp.message

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepo : JpaRepository<Message, Long>