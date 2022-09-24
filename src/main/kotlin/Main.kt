import org.sqlite.core.CoreResultSet
import java.sql.DriverManager

fun main(args: Array<String>) {
    val conn = DriverManager.getConnection("jdbc:sqlite:test.db")
    val s = conn.createStatement()
    val resultSet = s.executeQuery("SELECT * FROM Student;")
    println(resultSet.getString(1))
}
