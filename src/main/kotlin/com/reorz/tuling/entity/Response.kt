package com.reorz.tuling.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by acris on 2016/8/28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Response(
        val code: String,
        val text: String,
        val url: String?,
        val list: List<Message>?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Message(
        val article: String?,
        val source: String?,
        val icon: String?,
        val name: String?,
        val info: String?,
        val song: String?,
        val detailurl: String?
)