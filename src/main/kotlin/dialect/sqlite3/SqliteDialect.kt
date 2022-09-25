package dialect.sqlite3

import java.sql.Types.*
import core.table.TableColumn
import dialect.Dialect
import operator.*
import java.sql.ResultSet
import kotlin.math.min


object SqliteDialect : Dialect {
    //TODO
    //write test to this object

    override fun generateCreateSQL(op: CreateOperator): String {

        val columnList = op.columnList
        val tableName = op.tableName

        val columnBody: String = columnList
            .map { column -> stringifyTableColumn(column) }
            .fold("") { str, s -> "$str $s" }

        // only find first element in list which has primary key annotation
        val primaryKeyName = columnList
            .first { it.isPrimaryKey }
            .name

        val primaryKeyBody = "PRIMARY KEY(`$primaryKeyName`)"


        val temp = columnList
            .filter { it.isUnique }
            .fold("") { str, s -> "$str, `${s.name}`" }

        val uniqueKeyName = temp.substring(min(2, temp.length))

        val uniqueKeyBody =
            if (uniqueKeyName.isNotBlank()) "UNIQUE($uniqueKeyName)"
            else ""

        // warning! can't support normal index!
        // only support unique now

        return "CREATE TABLE `$tableName`($columnBody $primaryKeyBody $uniqueKeyBody);"
    }

    override fun generateQuerySQL(op: QueryOperator<*>): String {
        TODO("Not yet implemented")

    }

    override fun generateInsertSQL(op: InsertOperator): String {
        TODO("Not yet implemented")
    }

    override fun generateBatchInsertSQL(op: InsertOperator): String {
        TODO("Not yet implemented")
    }

    override fun generateUpdateSQL(op: UpdateOperator): String {
        TODO("Not yet implemented")
    }

    override fun generateDeleteSQL(op: DeleteOperator): String {
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
