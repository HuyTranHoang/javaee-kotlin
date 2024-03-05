import java.sql.DriverManager
import java.sql.SQLException

fun main() {
    val url = "jdbc:mysql://localhost:3306/c2202l_javaee_ebook?useSSL=false"
    val username = "root"
    val password = "g9v$;6~CmQRCq#U"

    try {
        val connection = DriverManager.getConnection(url, username, password)
        println("Connected to the database")
    } catch (e: SQLException) {
        println("Oops, there's an error")
        e.printStackTrace()
    }
}