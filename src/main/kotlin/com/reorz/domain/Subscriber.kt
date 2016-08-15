package com.reorz.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

/**
 * Created by acris on 2016/8/15.
 */
@Entity
data class Subscriber(
        @Id
        val id: Long,
        @Column(nullable = false, unique = true)
        val name: String,
        @Column(nullable = false)
        val type: String
)