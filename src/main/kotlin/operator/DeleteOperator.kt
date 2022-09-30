package operator

import dialect.GlobalDialect
import engine.Database
import logging.GlobalLogInstance

class DeleteOperator(
    internal val tableName: String
) : Operator {

    internal var whereCondition: String = ""

    fun where(condition: String): DeleteOperator {
        whereCondition = condition
        return this
    }

    override fun end() {
        val statement = GlobalDialect.dialect.generateDeleteSQL(this)
        GlobalLogInstance.log.infoPrepareStatement(statement)
        Database.executePrepareStatement(statement)
    }
}