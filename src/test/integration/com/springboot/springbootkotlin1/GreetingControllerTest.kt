package com.springboot.springbootkotlin1

import com.springboot.springbootkotlin1.Springbootkotlin1Application
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class GreetingControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    val name = "mehedee"

    @Test
    fun retrieveGreeting(){
        val result = webTestClient.get()
            .uri("v1/greetings/{name}",name)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()

        Assertions.assertEquals(
            "$name, Hello from default profile\n",
            result.responseBody
        )


    }
}