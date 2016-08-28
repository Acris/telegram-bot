package com.reorz.repository

import com.reorz.domain.Subscriber
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by acris on 2016/8/15.
 */
interface SubscriberRepository : JpaRepository<Subscriber, Long> {
}