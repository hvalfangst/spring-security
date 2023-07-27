package hvalfangst.security.config

import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.springframework.stereotype.Component

@Component
class DatabaseConfig(env: EnvironmentConfiguration) {
    init {
        // Connect to associated db
        Database.connect(
            url = env.databaseUrl,
            driver = env.databaseDriver,
            user = env.databaseUsername,
            password = env.databasePassword
        )

        // Create the Flyway instance and configure it
        val flyway = Flyway.configure()
            .dataSource(env.databaseUrl, env.databaseUsername, env.databasePassword)
            .load()

        // Migrate the database to the latest version
        flyway.migrate()
    }
}

