package com.magnushodne.chatapp.channel

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "channels")
class ChannelEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "channels_channel_id_seq")
    @SequenceGenerator(name = "channels_channel_id_seq", allocationSize = 1)
    @Column(name = "channel_id")
    val id: Long? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "description")
    val description: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "server_id" , referencedColumnName = "server_id")
    var server: ServerEntity? = null
}
