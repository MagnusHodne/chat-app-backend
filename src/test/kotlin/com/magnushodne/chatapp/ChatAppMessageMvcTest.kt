package com.magnushodne.chatapp

import org.json.JSONObject
import org.junit.jupiter.api.BeforeAll
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

    companion object {
        @BeforeAll
        @JvmStatic
        fun initData(@Autowired mockMvc: MockMvc) {
            mockMvc.post("http://localhost:5000/api/v1/users/login") {
                with(jwt().jwt(jwt))
                contentType = MediaType.APPLICATION_JSON
                content = JSONObject()
                    .put("sub", "user")
                    .put("username", "testuser")
                    .put("description", "")
                    .put("picture", "").toString()
            }

            mockMvc.post("http://localhost:5000/api/v1/servers") {
                with(jwt().jwt(jwt))
                contentType = MediaType.APPLICATION_JSON
                content = JSONObject()
                    .put("ownerId", 1)
                    .put("name", "testserver")
                    .put("description", "testdescription").toString()
            }

            mockMvc.post("http://localhost:5000/api/v1/channels") {
                with(jwt().jwt(jwt))
                contentType = MediaType.APPLICATION_JSON
                content = JSONObject()
                    .put("serverId", 1)
                    .put("name", "testchannel")
                    .put("description", "testdescription").toString()
            }
        }

        val jwt: Jwt = Jwt.withTokenValue("token")
            .header("alg", "none")
            .claim("sub", "user")
            .claim("scope", "read")
            .build()
    }

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
            with(jwt().jwt(jwt))
        }.andExpect { status { isOk() } }
    }

    @Test
    //@Sql("/database/insert_one_of_each_entity.sql") - Initial data can also be created with a script...
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
            .andExpect { content { jsonPath("$.content") { exists() } } }
            .andExpect { content { jsonPath("$.channel") { exists() } } }
    }
}