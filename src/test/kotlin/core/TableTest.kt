package core

import core.constraint.Name
import core.constraint.PrimaryKey
import core.constraint.Unique
import core.table.TableColumn
import engine.Database
import transaction.Transaction
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.Test

class TableTest {

    private val sqliteDatabaseName = "test.db"

    data class Student(
        @PrimaryKey val name: String, @Unique val age: Int, @Name("sex") val gender: Boolean
    )

    @Test
    fun testConvert() {
        val t = table(Student::class)
        assert(t.metaData.tableName == "Student")
        assert(
            t.metaData.columns[0] == TableColumn(
                "name", 0, java.sql.Types.VARCHAR, isNullable = false, isPrimaryKey = true
            )
        )
    }

    @Test
    fun testCreate() {
        Database.connect("jdbc:sqlite:$sqliteDatabaseName")

        val t = table(Student::class)
        Transaction {
            t.create().end()
        }
    }


    @Test
    fun testInsert() {
        Database.connect("jdbc:sqlite:$sqliteDatabaseName")

        val t = table(Student::class)
        Transaction {
            t
                .insert()
                .add(
                    listOf(
                        Student("下北泽", 1919810, true),
                        Student("野兽先辈", 1145104, true),
                        Student("昏睡红茶", 11451344, true)
                    )
                )
        }
    }


    fun removeTestDB() {
        Files.delete(Path.of(System.getProperty("user.dir") + File.separator + "test.db"))
    }

}

