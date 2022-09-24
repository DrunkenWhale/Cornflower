package log

import logging.Log
import logging.LogLevel
import kotlin.test.Test

class LogTest {

    val log = Log(level = LogLevel.ERROR)

    @Test
    fun logTest() {
        log.infoLog("114514 1919810")
    }
}