package com.magnushodne.chatapp.message

import com.fasterxml.jackson.annotation.JsonIgnore
import com.magnushodne.chatapp.user.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "messages")
class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messages_message_id_seq")
    @SequenceGenerator(name = "messages_message_id_seq", allocationSize = 1)
    @Column(name = "message_id")
    val id: Long? = null,

    @Column(name = "channel_id")
    val channelId: Long,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "content")
    val content: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "user_id")]
    )
    var author: User? = null

}