package com.reorz

import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.reorz.handler.MessageHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.telegram.telegrambots.TelegramApiException
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.logging.BotLogger
import org.telegram.telegrambots.updatesreceivers.BotSession
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@SpringBootApplication
@EnableScheduling
open class TelegramBotApplication {
    @Autowired lateinit var messageHandler: MessageHandler
    var botSession: BotSession? = null

    companion object {
        val LOGTAG: String = TelegramBotApplication::class.java.name

        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(TelegramBotApplication::class.java, *args)
        }
    }

    @PostConstruct
    fun start() {
        val telegramBotsApi = TelegramBotsApi()
        try {
            botSession = telegramBotsApi.registerBot(messageHandler)
            BotLogger.info(LOGTAG, "Telegram bot ${messageHandler.botUsername} started")
        } catch (e: TelegramApiException) {
            BotLogger.error(LOGTAG, "Start bot error", e)
        }
    }

    @PreDestroy
    fun stop() {
        botSession?.close()
        BotLogger.info(LOGTAG, "Telegram bot ${messageHandler.botUsername} stopped")
    }

    @Bean
    open fun kotlinModule() = KotlinModule()
}