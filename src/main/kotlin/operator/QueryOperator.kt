package operator

import core.table.TableColumn
import dialect.Dialect
import dialect.GlobalDialect
import engine.Database

class QueryOperator<T>(val columnList: List<TableColumn>) : Operator {

    fun where() {

    }

    fun groupBy() {

    }

    fun asc() {

    }

    fun desc() {

    }

    fun res(): List<T> {
        val sql = ""
        //TODO replace

        val resultSet = Database.executeQuery(sql)!!
        val tripleList = GlobalDialect.dialect.readResultSet(resultSet, columnList)

        return emptyList()
    }

    override fun end(): Boolean {
        TODO("Not yet implemented")
    }
}