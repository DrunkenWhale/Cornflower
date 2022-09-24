package operator

import core.table.TableColumn
import dialect.GlobalDialect
import engine.Database
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class QueryOperator<T : Any>(
    val tableName: String,
    val columnList: List<TableColumn>,
    val dataClass: KClass<T>
) : Operator {

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

        val list = mutableListOf<T>()
        while (resultSet.next()) {
            val tripleList = GlobalDialect.dialect.readResultSet(resultSet, columnList)
            val data = dataClass.primaryConstructor!!.call(tripleList.map { it.third })
            list.add(data)
        }
        return list.toList()
    }

    override fun end(): Boolean {
        TODO("Not yet implemented")
    }
}