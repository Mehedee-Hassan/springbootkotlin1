package com.springboot.kotlin.test1.springbootkotlin1.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class GreetingService {
    @Value("\${message}")
    lateinit var message: String

    fun greetingMessage(name :String) : String{
        return "Hello $name $message\n"
    }
}