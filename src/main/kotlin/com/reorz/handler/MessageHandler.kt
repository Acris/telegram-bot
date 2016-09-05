package com.reorz.handler

import com.reorz.turing.client.TuringClient
import com.reorz.turing.entity.MessageCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.telegram.telegrambots.TelegramApiException
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.logging.BotLogger

/**
 * Created by acris on 2016/8/13.
 */
@Component
class MessageHandler : TelegramLongPollingBot() {
    @Autowired lateinit var environment: Environment
    @Autowired lateinit var turingClient: TuringClient

    companion object {
        val LOGTAG: String = MessageHandler::class.java.name
    }

    override fun getBotUsername(): String {
        return environment.getProperty("app.bot.username")
    }

    override fun getBotToken(): String {
        return environment.getProperty("app.bot.token")
    }

    override fun onUpdateReceived(update: Update) {
        when {
            update.hasMessage() -> {
                val message = update.message
                when {
                    // user message
                    message.isUserMessage -> handleUserMessage(message)
                    // group message
                    message.isGroupMessage -> handleGroupMessage(message)
                    // super group message
                    message.isSuperGroupMessage -> handleSuperGroupMessage(message)
                    // channel message
                    message.isChannelMessage -> handleChannelMessage(message)
                    else -> {
                        BotLogger.info(LOGTAG, "Unknown message type.")
                    }
                }
            }
            update.hasEditedMessage() -> {
            }
            update.hasInlineQuery() -> {
            }
            update.hasCallbackQuery() -> {
            }
            update.hasChosenInlineQuery() -> {
            }
            else -> {
                BotLogger.info(LOGTAG, "Update ${update.updateId} is empty.")
            }
        }
    }

    /**
     * User message handler
     */
    fun handleUserMessage(message: Message) {
        when {
            message.hasText() -> {
                val text = message.text
                when {
                    // if message is command
                    message.isCommand -> {
                        when {
                            text.startsWith("/start") -> {
                            }
                            text.startsWith("/help") -> {
                            }
                            else -> {
                                BotLogger.info(LOGTAG, "Unknown command.")
                            }
                        }
                    }
                    else -> {
                        val key = environment.getProperty("app.turing.key")
                        val info = message.text
                        val userid = message.chatId.toString()
                        val loc: String? = null
                        val response = turingClient.chat(key, info, userid, loc)

                        val sm = SendMessage()
                        sm.chatId = message.chatId.toString()

                        val code = response!!.code
                        when (code) {
                            MessageCode.TEXT.code -> {
                                sm.text = response.text
                            }
                            MessageCode.LINK.code -> {
                                sm.text = response.text
                                sm.text += "\n"
                                sm.text += "\n 链接：${response.url}"
                            }
                            MessageCode.NEWS.code -> {
                                sm.text = response.text
                                sm.text += "\n"
                                val list = response.list
                                list?.forEach {
                                    print(it)
                                    sm.text += "\n 标题：${it.article}"
                                    sm.text += "\n 来源：${it.source}"
                                    sm.text += "\n 详情：${it.detailurl}"
                                    sm.text += "\n "
                                }
                            }
                            MessageCode.RECIPE.code -> {
                                sm.text = response.text
                                sm.text += "\n"
                                val list = response.list
                                list?.forEach {
                                    sm.text += "\n 菜名：${it.name}"
                                    sm.text += "\n 信息：${it.info}"
                                    sm.text += "\n 详情：${it.detailurl}"
                                    sm.text += "\n "
                                }
                            }
                            else -> {
                                sm.text = response.text
                                BotLogger.info(LOGTAG, "Unmatched message code.")
                            }
                        }

                        try {
                            sendMessage(sm)
                        } catch (e: TelegramApiException) {
                            BotLogger.error(LOGTAG, "Send message error.", e)
                        }
                    }
                }

            }
        }
    }

    /**
     * Group message handler
     */
    fun handleGroupMessage(message: Message) {

    }

    /**
     * Super group message handler
     */
    fun handleSuperGroupMessage(message: Message) {

    }

    /**
     * Channel message handler
     */
    fun handleChannelMessage(message: Message) {

    }
}