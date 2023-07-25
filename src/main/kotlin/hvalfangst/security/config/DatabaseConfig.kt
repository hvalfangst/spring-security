package hvalfangst.security.config

import org.jetbrains.exposed.sql.Database

// TODO: Must inherit environment variables derived from applications.yml
object DatabaseConfig {
    fun init() {
        val dbUrl = "jdbc:postgresql://localhost:5433/mydb"
        val dbUser = "myuser"
        val dbPassword = "mypassword"
        val driver = "org.postgresql.Driver"

        Database.connect(url = dbUrl, driver = driver, user = dbUser, password = dbPassword)
    }
}
