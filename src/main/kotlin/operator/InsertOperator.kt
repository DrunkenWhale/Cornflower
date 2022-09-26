package operator

import core.table.TableColumn
import dialect.GlobalDialect
import engine.Database
import logging.GlobalLogInstance
import kotlin.reflect.full.declaredFunctions

class InsertOperator<T : Any>(
    internal val tableName: String,
    internal val columnList: List<TableColumn>,
) : Operator {

    internal val list: MutableList<T> = mutableListOf()
    internal lateinit var dataList: List<List<Any>>

    fun add(data: T): InsertOperator<T> {
        list.add(data)
        return this
    }

    fun add(dataList: List<T>): InsertOperator<T> {
        list.addAll(dataList)
        return this
    }

    override fun end() {
        this.dataList = list.map { callDataClassComponentNMethodGetData(it) }
        val statement = GlobalDialect.dialect.generateInsertSQL(this)
        GlobalLogInstance.log.infoPrepareStatement(statement)
        Database.executePrepareStatement(statement)
    }

    /**
     * data muse be data class instance!
     * convert a data class to List<Any>
     * */
    private fun callDataClassComponentNMethodGetData(data: T): List<Any> {
        return data::class
            .declaredFunctions
            .filter { it.name.contains("component") }
            .map { it.call(data)!! }
    }
    //stu::class.declaredFunctions.filter { it.name.contains("component") }.map{it.call(stu)}
}