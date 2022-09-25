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

    override fun end(): Boolean {
        this.dataList = list.map { callDataClassComponentNMethodGetData(it) }
        val sql = GlobalDialect.dialect.generateInsertSQL(this)
        GlobalLogInstance.log.infoLog(sql)
        return Database.execute(sql)
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