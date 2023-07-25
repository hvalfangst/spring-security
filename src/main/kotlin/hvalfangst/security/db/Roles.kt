package hvalfangst.security.db

import org.jetbrains.exposed.sql.Table

object Roles :  Table(name = "roles") {
    val id = integer("role_id").autoIncrement()
    val name = varchar("name", 45)
    override val primaryKey = PrimaryKey(id)
}
