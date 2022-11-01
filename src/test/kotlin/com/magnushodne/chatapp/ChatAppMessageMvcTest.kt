package com.magnushodne.chatapp

import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ChatAppMessageMvcTest(@Autowired private val mockMvc: MockMvc) {

    val baseUrl = "http://localhost:5000/api/v1/chats"

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
            with(jwt())
        } .andExpect { status { isOk() } }
    }
}