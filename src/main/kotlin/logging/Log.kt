package logging

import java.sql.PreparedStatement
import java.sql.Timestamp

/**
 *
 * logging util
 *
 * @param level log level, The options are OFF, ERROR, WARNING, INFO
 *
 *
 * ```kotlin
 *
 * val log = Log(level=WARNING)
 *
 * log.warningLog("114514")
 * // output [(Time)] [WARNING]  114514
 *
 * log.errorLog("1919810")
 * // output [(Time)] [ERROR]  1919810
 *
 * log.infoLog("1919810")
 * // don't output
 * // INFO level higher than WARNING
 *
 * ```
 *
 * */
class Log(
    val level: LogLevel = LogLevel.INFO
) {

    fun log(message: Any, logLevel: LogLevel = this.level) {
        // need higher level to output log
        if (logLevel > this.level) {
            return
        }
        val logMessage = generateLogString(message.toString())
        println(logMessage)
    }

    fun generateLogString(message: Any): String {
        return String.format("[%23s]", Timestamp(System.currentTimeMillis())) + message
    }

    fun errorLog(message: Any) {
        log("$errorColor [ERROR]    $message$reset", LogLevel.ERROR)
    }

    fun waringLog(message: Any) {
        log("$warningColor [WARNING]  $message$reset", LogLevel.WARNING)
    }

    fun infoLog(message: Any) {
        log("$infoColor [INFO]   $message$reset$reset", LogLevel.INFO)
    }

    fun infoPrepareStatement(statement: PreparedStatement) {
        infoLog(statement.toString().split("parameter")[0])
    }

    companion object Log {
        const val infoColor = "\u001b[34m"
        const val warningColor = "\u001b[33m"
        const val errorColor = "\u001b[31m"
        const val reset = "\u001b[0m"
    }

}
