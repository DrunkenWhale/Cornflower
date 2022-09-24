package dialect.sqlite3

import core.table.TableColumn
import dialect.Dialect
import java.sql.ResultSet

object SqliteDialect : Dialect {
    override fun generateCreateSQL(tableName: String, columnList: List<TableColumn>): String {
        TODO("Not yet implemented")
    }

    override fun generateQuerySQL(tableName: String, columnList: List<TableColumn>): String {
        TODO("Not yet implemented")
    }

    override fun generateInsertSQL(tableName: String, columnList: List<TableColumn>, values: List<Any>): String {
        TODO("Not yet implemented")
    }

    override fun generateBatchInsertSQL(
        tableName: String,
        columnList: List<TableColumn>,
        values: List<List<Any>>
    ): String {
        TODO("Not yet implemented")
    }

    override fun generateUpdateSQL(tableName: String, columnList: List<TableColumn>, values: List<Any>): String {
        TODO("Not yet implemented")
    }

    override fun generateDeleteSQL(tableName: String, columnList: List<TableColumn>): String {
        TODO("Not yet implemented")
    }

    override fun readResultSet(resultSet: ResultSet, columnList: List<TableColumn>): List<Triple<Int, Int, Any>> {
        TODO("Not yet implemented")
    }

}