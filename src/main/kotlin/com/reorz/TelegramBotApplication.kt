package com.reorz

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class TelegramBotApplication {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(TelegramBotApplication::class.java, *args)
        }
    }
}