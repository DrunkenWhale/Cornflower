import org.sqlite.core.CoreResultSet
import java.sql.DriverManager
import kotlin.reflect.full.primaryConstructor

//fun main(args: Array<String>) {
//    val conn = DriverManager.getConnection("jdbc:sqlite:test.db")
//    val s = conn.createStatement()
//    println(conn.metaData.databaseProductName)
//}

data class Student(val name: String, val age: Int, val gender: Boolean)

fun main() {
    val constructor = Student::class.primaryConstructor!!

    val a = constructor.call("114514" as Any, 1919810 as Any, true as Any)
    println(a)
}