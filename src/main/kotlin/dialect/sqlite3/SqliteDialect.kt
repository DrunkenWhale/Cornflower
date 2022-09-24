package dialect.sqlite3

import java.sql.Types.*
import core.table.TableColumn
import dialect.Dialect
import java.sql.ResultSet


object SqliteDialect : Dialect {
    //TODO
    //write test to this object

    override fun generateCreateSQL(tableName: String, columnList: List<TableColumn>): String {

        val columnBody: String = columnList
            .map { column -> stringifyTableColumn(column) }
            .fold("") { str, s -> "$str $s" }

        // only find first element in list which has primary key annotation
        val primaryKeyName = columnList
            .first { it.isPrimaryKey }
            .name

        val primaryKeyBody = "PRIMARY KEY(`$primaryKeyName`)"

        val uniqueKeyName = columnList
            .filter { it.isUnique }
            .fold("") { str, s -> "$str, `${s.name}`" }
            .substring(2)

        val uniqueKeyBody = "UNIQUE($uniqueKeyName)"

        // warning! can't support normal index!
        // only support unique now

        return "CREATE TABLE `$tableName`($columnBody $primaryKeyBody $uniqueKeyBody);"
    }

    override fun generateQuerySQL(tableName: String, columnList: List<TableColumn>): String {
        TODO("Not yet implemented")
    }

    override fun generateInsertSQL(tableName: String, columnList: List<TableColumn>, values: List<Any>): String {
        TODO("Not yet implemented")
    }

    override fun generateBatchInsertSQL(
        tableName: String, columnList: List<TableColumn>, values: List<List<Any>>
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

    private fun sqlTypeMappingToSqliteType(sqlType: Int): String = when (sqlType) {
        INTEGER -> "INT"
        BIGINT -> "BIGINT"
        DOUBLE -> "DOUBLE"
        FLOAT -> "FLOAT"
        BOOLEAN -> "BOOLEAN"
        // TODO add annotation to ensure TEXT type can be used
        VARCHAR -> "VARCHAR(255)"
        else -> throw TypeNotPresentException(sqlType.toString(), null)
    }

    private fun stringifyTableColumn(column: TableColumn): String {
        val name = column.name
        val type = sqlTypeMappingToSqliteType(column.type)
        val nullable = if (!column.isNullable) {
            "NOT NULL"
        } else {
            ""
        }
        return "`$name` $type $nullable,"
    }
}
