package com.magnushodne.chatapp.message

import com.magnushodne.chatapp.channel.ChannelEntity
import com.magnushodne.chatapp.user.UserEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "messages")
class MessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messages_message_id_seq")
    @SequenceGenerator(name = "messages_message_id_seq", allocationSize = 1)
    @Column(name = "message_id")
    val id: Long? = null,

    @Column(name = "content")
    val content: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    var author: UserEntity? = null

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "channel_id", referencedColumnName = "channel_id")
    var channel: ChannelEntity? = null

}
