package com.magnushodne.chatapp.user

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "users_user_id_seq", allocationSize = 1)
    @Column(name = "user_id")
    val id: Long? = null,

    @Column(name = "sub")
    val sub: String,

    @Column(name = "username")
    val username: String,

    @Column(name = "picture")
    val picture: String,

    @Column(name = "description")
    val description: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "last_login")
    var lastLogin: LocalDateTime = LocalDateTime.now()
) {

}