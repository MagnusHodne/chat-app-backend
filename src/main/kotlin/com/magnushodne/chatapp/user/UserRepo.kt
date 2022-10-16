package com.magnushodne.chatapp.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo: JpaRepository<User, Long> {
    fun existsBySubEquals(sub: String): Boolean
    fun findBySubEquals(sub: String): User?
    fun save(user: User): User
}