package com.reorz.turing.entity

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
        val detailurl: String?
)

enum class MessageCode(val code: String) {
    TEXT("100000"),
    LINK("200000"),
    NEWS("302000"),
    RECIPE("308000")
}