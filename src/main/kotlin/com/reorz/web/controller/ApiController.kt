package com.reorz.web.controller

import org.springframework.web.bind.annotation.RestController

/**
 * Created by acris on 2016/8/10.
 */
@RestController
class ApiController {
    companion object {
        val LOGTAG: String? = ApiController::class.java.name
    }
}