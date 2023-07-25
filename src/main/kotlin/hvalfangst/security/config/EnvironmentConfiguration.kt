package hvalfangst.security.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "env")
class EnvironmentConfiguration {
        var databaseUrl = "nil"
        var databaseUsername = "nil"
        var databasePassword = "nil"
        var databaseDriver = "nil"
}