package com.springboot.kotlin.test1.springbootkotlin1.controller

import com.springboot.kotlin.test1.springbootkotlin1.service.GreetingService
import mu.KLoggable
import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/greetings")
class GreetingController (val greetingService: GreetingService){
    companion object : KLogging()
    @GetMapping("/{name}")
    fun retrieveGreeting(@PathVariable("name") name: String): String{
        logger.info("name is $name")
        return greetingService.greetingMessage(name)

    }
}