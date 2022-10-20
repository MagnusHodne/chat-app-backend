package com.magnushodne.chatapp.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, Long> {
    fun existsBySubEquals(sub: String): Boolean
    fun findBySubEquals(sub: String): UserEntity?
    fun save(userEntity: UserEntity): UserEntity
}