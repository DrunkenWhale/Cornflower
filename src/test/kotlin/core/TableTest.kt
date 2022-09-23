package core

import core.constraint.PrimaryKey
import core.table.TableColumn
import kotlin.test.Test


class TableTest {
    @Test
    fun testConvert() {
        data class Student(@PrimaryKey val name: String, val age: Int, val gender: Boolean)

        val t = table(Student::class)
        assert(t.metaData.tableName == "Student")
        assert(
            t.metaData.dataClass.toString()
                    == "class core.TableTest\$testConvert\$Student"
        )
        assert(
            t.metaData.columns[0] == TableColumn(
                "name", 0, java.sql.Types.VARCHAR,
                isNullable = false,
                isPrimaryKey = true
            )
        )
    }
}

