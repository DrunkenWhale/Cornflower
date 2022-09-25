package core

import core.constraint.Name
import core.constraint.PrimaryKey
import core.constraint.Unique
import core.table.TableColumn
import engine.Database
import operator.CreateOperator
import org.junit.jupiter.api.Test
import transaction.Transaction


class TableTest {
    @Test
    fun testConvert() {
        data class Student(@PrimaryKey val name: String, val age: Int, val gender: Boolean)

        val t = table(Student::class)
        assert(t.metaData.tableName == "Student")
        assert(
            t.metaData.dataClass.toString() == "class core.TableTest\$testConvert\$Student"
        )
        assert(
            t.metaData.columns[0] == TableColumn(
                "name", 0, java.sql.Types.VARCHAR, isNullable = false, isPrimaryKey = true
            )
        )
    }

    @Test
    fun testCreate() {
        Database.connect("jdbc:sqlite:test.db")
        data class Student(
            @PrimaryKey val name: String, @Unique val age: Int, @Unique @Name("sex") val gender: Boolean
        )

        val t = table(Student::class)
        Transaction {
            t.create().end()
        }
    }

    @Test
    fun testCreateSentence() {
        Database.connect("jdbc:sqlite:test.db")
        data class Student(
            @PrimaryKey val name: String, @Unique val age: Int, @Name("sex") val gender: Boolean
        )

        val t = table(Student::class)
        dialect.sqlite3.SqliteDialect.generateCreateSQL(
            CreateOperator(
                t.metaData.tableName,
                t.metaData.columns
            )
        ).toString()
        assert(
            dialect.sqlite3.SqliteDialect.generateCreateSQL(
                CreateOperator(
                    t.metaData.tableName,
                    t.metaData.columns
                )
            ).toString() == "CREATE TABLE `Student`( `name` VARCHAR(255) NOT NULL, `age` INT NOT NULL, `sex` BOOLEAN NOT NULL, PRIMARY KEY(`name`) UNIQUE(`age`, `sex`)); "
        )

    }
}

