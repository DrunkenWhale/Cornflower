package operator

import dialect.GlobalDialect
import engine.Database
import logging.GlobalLogInstance

class UpdateOperator(
    internal val tableName: String
) : Operator {


    internal var whereCondition: String = ""
    internal var replaceCondition: String = ""

    fun replace(condition: String): UpdateOperator {
        replaceCondition = condition
        return this
    }

    fun where(condition: String): UpdateOperator {
        whereCondition = condition
        return this
    }

    override fun end() {
        val statement = GlobalDialect.dialect.generateUpdateSQL(this)
        GlobalLogInstance.log.infoPrepareStatement(statement)
        Database.executePrepareStatement(statement)
    }
}