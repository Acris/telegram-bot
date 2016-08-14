package com.reorz.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.telegram.telegrambots.logging.BotLogger

/**
 * Created by acris on 2016/8/9.
 */
@Controller
class HomeController {
    companion object {
        val LOGTAG: String? = HomeController::class.java.name
    }

    /**
     * Go to home page
     */
    @RequestMapping("/")
    fun home(): String {
        BotLogger.info(LOGTAG, "Go to home page")
        return "index"
    }
}