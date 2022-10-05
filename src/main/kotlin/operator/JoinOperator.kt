package operator

import core.table.TableColumn
import dialect.GlobalDialect
import engine.Database
import logging.GlobalLogInstance

class JoinOperator(
    internal val leftTableName: String,
    internal val rightTableName: String,
    internal val leftTableColumnList: List<TableColumn>,
    internal var rightTableColumnList: List<TableColumn>,
    internal val join: String = "INNER JOIN"
) : Operator {

    internal var whereCondition: String = ""
    internal var on: String = ""
    internal var ascOrDesc: String = ""
    internal var orderByColumnName: String = ""
    internal var groupByColumnName: String = ""
    internal val leftColumnList: MutableList<TableColumn> = mutableListOf()
    internal var rightColumnList: MutableList<TableColumn> = mutableListOf()

    fun leftColumns(vararg columnName: String): JoinOperator {
        val columnNameSet = columnName.toSet()
        leftColumnList.addAll(
            leftTableColumnList.filter { it.name in columnNameSet }
        )
        return this
    }

    fun rightColumns(vararg columnName: String): JoinOperator {
        val columnNameSet = columnName.toSet()
        rightColumnList.addAll(
            rightTableColumnList.filter { it.name in columnNameSet }
        )
        return this
    }

    fun on(condition: String): JoinOperator {
        this.on = condition
        return this

    }

    fun where(condition: String): JoinOperator {
        whereCondition = condition
        return this
    }

    fun groupBy(column: String): JoinOperator {
        groupByColumnName = column
        return this
    }

    fun orderBy(column: String): JoinOperator {
        orderByColumnName = column
        return this
    }


    fun asc(): JoinOperator {
        ascOrDesc = "ASC"
        return this
    }

    fun desc(): JoinOperator {
        ascOrDesc = "DESC"
        return this
    }

    override fun end() {
        val statement = GlobalDialect.dialect.generateJoinSQL(this)
        GlobalLogInstance.log.infoPrepareStatement(statement)
        Database.executePrepareStatement(statement)
    }

    fun res(): List<List<Any>> {
        val statement = GlobalDialect.dialect.generateJoinSQL(this)
        GlobalLogInstance.log.infoPrepareStatement(statement)
        val resultSet = Database.executePrepareStatementQuery(statement)
        return GlobalDialect.dialect.readResultSet(resultSet, leftColumnList + rightColumnList)
    }

}