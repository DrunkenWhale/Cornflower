package operator

import core.table.TableColumn
import dialect.GlobalDialect
import engine.Database
import kotlin.reflect.KClass

class CreateOperator(
    val tableName: String,
    val columnList: List<TableColumn>,
) : Operator {

    override fun end(): Boolean {
        val sql = GlobalDialect.dialect.generateCreateSQL(tableName, columnList)
        return Database.execute(sql)
    }

}