package com.magnushodne.chatapp.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

    // Called every time a user logs in
    fun registerLogin(sub: UserInfoRequestBody): User? {
        val existingUser = userRepository.findBySubEquals(sub)

        existingUser?.let {
            existingUser.lastLogin = LocalDateTime.now()
            return try {
                userRepository.save(existingUser)
            } catch (e: Exception) {
                null
            }
        }
        val newUser = User(sub = sub, username = "New user", picture = "", description = "")

        return try {
            userRepository.save(newUser)
        } catch (e: Exception) {
            null
        }
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    fun getUserById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun getUserBySub(sub: String): User? {
        return userRepository.findBySubEquals(sub)
    }

    fun createUser(user: User): User {
        return userRepository.save(user)
    }
}