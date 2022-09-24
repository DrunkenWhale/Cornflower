package operator

import core.table.TableColumn
import dialect.GlobalDialect
import engine.Database
import logging.GlobalLogInstance
import logging.Log
import kotlin.reflect.KClass

class CreateOperator(
    private val tableName: String,
    private val columnList: List<TableColumn>,
) : Operator {

    override fun end(): Boolean {
        val sql = GlobalDialect.dialect.generateCreateSQL(tableName, columnList)
        GlobalLogInstance.log.infoLog(sql)
        return Database.execute(sql)
    }

}