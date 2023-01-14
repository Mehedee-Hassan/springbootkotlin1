package com.springboot.springbootkotlin1

import com.ninjasquad.springmockk.MockkBean
import com.springboot.springbootkotlin1.controller.GreetingController
import com.springboot.springbootkotlin1.service.GreetingService
import io.mockk.every
//import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [GreetingController::class])
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class GreetingControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var greetingServiceMock: GreetingService

    @Test
    fun retrieveGreeting(){
        val name:String = "mehedee"

        every {
            greetingServiceMock.retrieveGreeting(any())
        } returns "$name, Hello from STG profile"

        val result = webTestClient.get()
            .uri("/v1/greetings/{name}",name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()

        Assertions.assertEquals(
            "$name, Hello from STG profile",
            result.responseBody
        )
    }

}

