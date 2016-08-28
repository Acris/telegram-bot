package com.reorz.task

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Clock

/**
 * Created by acris on 2016/8/11.
 */
@Component
class ScheduledTask {
    companion object {
        val LOGTAG: String = ScheduledTask::class.java.name
    }

    @Scheduled(cron = "0 * * * * *")
    fun reportTimePerHour() {
        val clock: Clock = Clock.systemDefaultZone()
        print(clock.instant())
    }
}