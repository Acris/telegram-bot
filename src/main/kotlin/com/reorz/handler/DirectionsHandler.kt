package com.reorz.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot

/**
 * Created by acris on 2016/8/13.
 */
@Component
class DirectionsHandler : TelegramLongPollingBot() {
    @Autowired lateinit var environment: Environment

    override fun getBotUsername(): String {
        return environment.getProperty("app.bot.username")
    }

    override fun getBotToken(): String {
        return environment.getProperty("app.bot.token")
    }

    override fun onUpdateReceived(update: Update?) {

    }
}