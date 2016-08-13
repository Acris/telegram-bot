package com.reorz.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.telegram.telegrambots.TelegramApiException
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot

/**
 * Created by acris on 2016/8/13.
 */
@Component
class MessageHandler : TelegramLongPollingBot() {
    @Autowired lateinit var environment: Environment

    override fun getBotUsername(): String {
        return environment.getProperty("app.bot.username")
    }

    override fun getBotToken(): String {
        return environment.getProperty("app.bot.token")
    }

    override fun onUpdateReceived(update: Update?) {
        if (update!!.hasMessage()) {
            val message = update.message
            if (message.hasText()) {
                val sm = SendMessage()
                sm.chatId = message.chatId.toString()
                sm.text = message.text
                try {
                    sendMessage(sm)
                } catch (e: TelegramApiException) {

                }
            }
        }
    }
}