package operator

import core.table.TableColumn
import dialect.GlobalDialect
import engine.Database
import logging.GlobalLogInstance

class CreateOperator(
    internal val tableName: String,
    internal val columnList: List<TableColumn>,
) : Operator {

    override fun end() {
        val statement = GlobalDialect.dialect.generateCreateSQL(this)
        GlobalLogInstance.log.infoPrepareStatement(statement)
        Database.executePrepareStatement(statement)
    }

}