package com.magnushodne.chatapp.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

    // Called every time a user logs in
    fun registerLogin(user: UserInfoRequestBody): UserEntity? {
        val existingUser = userRepository.findBySubEquals(user.sub)

        existingUser?.let {
            existingUser.lastLogin = LocalDateTime.now()
            return try {
                userRepository.save(existingUser)
            } catch (e: Exception) {
                null
            }
        }
        val newUserEntity = UserEntity(sub = user.sub, username = user.name, picture = "", description = "")

        return try {
            userRepository.save(newUserEntity)
        } catch (e: Exception) {
            null
        }
    }

    fun getAllUsers(): List<UserEntity> {
        return userRepository.findAll()
    }

    fun getUserById(id: Long): UserEntity? {
        return userRepository.findById(id).orElse(null)
    }

    fun getUserBySub(sub: String): UserEntity? {
        return userRepository.findBySubEquals(sub)
    }

    fun createUser(userEntity: UserEntity): UserEntity {
        return userRepository.save(userEntity)
    }
}