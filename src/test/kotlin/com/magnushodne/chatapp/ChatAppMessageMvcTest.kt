package com.magnushodne.chatapp

import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChatAppMessageMvcTest(@Autowired private val mockMvc: MockMvc) {

    val baseUrl = "http://localhost:5000/api/v1/chats"
    val jwt: Jwt = Jwt.withTokenValue("token")
            .header("alg", "none")
            .claim("sub", "user")
            .claim("scope", "read")
            .build()

    @Test
    @Throws(Exception::class)
    fun getMessages_withoutValidJwt_returnsUnauthorized() {
        mockMvc.get(baseUrl)
            .andExpect {
                status { isUnauthorized() }
            }
    }

    @Test
    @Throws(Exception::class)
    fun getMessages_withValidJwtToken_returnsOk() {
        mockMvc.get(baseUrl) {
            with(jwt().jwt(jwt))
        }.andExpect { status { isOk() } }
    }

    @Test
    @Sql("/database/insert_one_of_each_entity.sql")
    fun postMessage_withValidJwtToken_returnsCreated() {
        val messagePayload = JSONObject().put("content", "Hello world").put("channelId", 1).put("authorId", 1)

        mockMvc.post(baseUrl) {
            with(jwt().jwt(jwt))
            contentType = MediaType.APPLICATION_JSON
            content = messagePayload.toString()
        }
            .andExpect { status { isCreated() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { content { jsonPath("$.id") { exists() } } }
            .andExpect { content { jsonPath("$.id") { exists() } } }
    }
}