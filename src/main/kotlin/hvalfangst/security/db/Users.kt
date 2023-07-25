package hvalfangst.security.db

import org.jetbrains.exposed.sql.Table

object Users : Table(name = "users") {
    val id = integer("user_id").autoIncrement()
    val email = varchar("email", 45).uniqueIndex()
    val fullName = varchar("full_name", 45)
    val password = varchar("password", 64)
    val enabled = bool("enabled").nullable()
    override val primaryKey = PrimaryKey(id)
}