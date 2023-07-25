package hvalfangst.security.config

import org.jetbrains.exposed.sql.Database
import org.springframework.stereotype.Component

@Component
class DatabaseConfig(env: EnvironmentConfiguration) {
    init {
        Database.connect(url = env.databaseUrl, driver = env.databaseDriver, user = env.databaseUsername, password = env.databasePassword)
    }
}

