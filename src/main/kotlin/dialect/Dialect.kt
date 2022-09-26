package dialect

import core.table.TableColumn
import operator.*
import java.sql.PreparedStatement
import java.sql.ResultSet

interface Dialect {

    /**
     *
     * from table's MetaData to `CREATE` SQL Sentence
     */
    fun generateCreateSQL(op: CreateOperator): PreparedStatement

    fun generateQuerySQL(op: QueryOperator<*>): PreparedStatement

    fun <T : Any> generateInsertSQL(op: InsertOperator<T>): PreparedStatement

    fun generateUpdateSQL(op: UpdateOperator): PreparedStatement

    fun generateDeleteSQL(op: DeleteOperator): PreparedStatement

    /**
     * @return List<Int, SQL Enum Type, Value>
     * */
    fun readResultSet(resultSet: ResultSet, columnList: List<TableColumn>): List<Triple<Int, Int, Any>>


    fun registerToGlobal() {
        GlobalDialect.dialect = this
    }

}
