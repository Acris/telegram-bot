package com.reorz.tuling.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.reorz.tuling.entity.Request
import com.reorz.tuling.entity.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

/**
 * Created by acris on 2016/8/28.
 */
@Component
class TulingClient {
    @Autowired lateinit var restTemplate: RestTemplate
    val mapper = jacksonObjectMapper()
    val tulingApi = "http://www.tuling123.com/openapi/api"

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder.build()
    }

    fun chat(key: String, info: String, userid: String, loc: String?): Response? {
        var request = Request(key, info, userid, loc)
        val responseStr = restTemplate.postForObject(tulingApi, request, String::class.java)
        val response = mapper.readValue(responseStr, Response::class.java)
        return response
    }
}