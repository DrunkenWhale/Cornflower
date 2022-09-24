import org.sqlite.core.CoreResultSet
import java.sql.DriverManager

fun main(args: Array<String>) {
    val conn = DriverManager.getConnection("jdbc:sqlite:test.db")
    val s = conn.createStatement()
    println(conn.metaData.databaseProductName)
}
