package dialect

import core.table.TableColumn
import java.sql.ResultSet

interface Dialect {

    /**
     *
     * from table's MetaData to `CREATE` SQL Sentence
     */
    fun generateCreateSQL(tableName: String, columnList: List<TableColumn>): String

    /**
     *
     * */
    fun generateQuerySQL(tableName: String, columnList: List<TableColumn>): String

    /**
     *  @param columnList table's metadata
     *  @param values column values that have the same order with columnList
     * */
    fun generateInsertSQL(tableName: String, columnList: List<TableColumn>, values: List<Any>): String

    fun generateBatchInsertSQL(tableName: String, columnList: List<TableColumn>, values: List<List<Any>>): String

    fun generateUpdateSQL(tableName: String, columnList: List<TableColumn>, values: List<Any>): String

    fun generateDeleteSQL(tableName: String, columnList: List<TableColumn>): String

    fun readResultSet(resultSet: ResultSet, columnList: List<TableColumn>): List<Triple<Int, Int, Any>>


}
