package dialect

import core.table.TableColumn
import operator.*
import java.sql.ResultSet

interface Dialect {

    /**
     *
     * from table's MetaData to `CREATE` SQL Sentence
     */
    fun generateCreateSQL(op: CreateOperator): String

    /**
     *
     * */
    fun generateQuerySQL(op: QueryOperator<*>): String

    /**
     *  @param columnList table's metadata
     *  @param values column values that have the same order with columnList
     * */
    fun <T> generateInsertSQL(op: InsertOperator<T>): String

    fun <T> generateBatchInsertSQL(op: InsertOperator<T>): String

    fun generateUpdateSQL(op: UpdateOperator): String

    fun generateDeleteSQL(op: DeleteOperator): String

    /**
     * @return List<Int, SQL Enum Type, Value>
     * */
    fun readResultSet(resultSet: ResultSet, columnList: List<TableColumn>): List<Triple<Int, Int, Any>>


    fun registerToGlobal() {
        GlobalDialect.dialect = this
    }

}
