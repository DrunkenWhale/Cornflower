package core

import core.constraint.Name
import core.constraint.PrimaryKey
import core.constraint.Unique
import core.table.TableColumn
import engine.Database
import operator.condition.Condition.or
import operator.condition.Condition.and
import operator.condition.Condition.eq
import trans.Transaction
import trans.transaction
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
            t.insert().add(
                listOf(
                    Student("下北泽", 1919810, true), Student("野兽先辈", 1145104, true), Student("昏睡红茶", 11451344, true)
                )
            )
        }
    }

    @Test
    fun testQuery() {
        Database.connect("jdbc:sqlite:$sqliteDatabaseName")

        val t = table(Student::class)
        transaction {

            val list = listOf(
                Student("嗯嗯嗯", 1919810, false),
                Student("啊啊啊", 1145104, false),
                Student("啊啊啊啊", 11451344, true)
            )

            t.create().end()

            t.insert().add(list).end()

            assert(t.select().res() == list)

            assert(
                t.select().where("name" eq "啊啊啊").res()
                        == list.filter { it.name == "啊啊啊" }
            )

            assert(
                t.select().where(("name" eq "嗯嗯嗯") or ("name" eq "啊啊啊") and ("age" eq 1919810)).res()
                        == list.filter { it.name == "嗯嗯嗯" || it.name == "啊啊啊" && it.age == 1919810 }
            )

            t.update()
                .replace("sex" eq true)
                .end()

            assert(
                t.select().where("sex" eq true).res().size == 3
            )
        }

    }


    fun removeTestDB() {
        Files.delete(Path.of(System.getProperty("user.dir") + File.separator + "test.db"))
    }

}

