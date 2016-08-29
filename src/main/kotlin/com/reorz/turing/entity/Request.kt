package com.reorz.turing.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by acris on 2016/8/28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Request(
        val key: String,
        val info: String,
        val userid: String,
        val loc: String?
)