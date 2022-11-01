package com.magnushodne.chatapp.server

import com.magnushodne.chatapp.user.UserEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "servers")
class ServerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servers_server_id_seq")
    @SequenceGenerator(name = "servers_server_id_seq", allocationSize = 1)
    @Column(name = "server_id")
    val id: Long? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "description")
    val description: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    var owner: UserEntity? = null
}