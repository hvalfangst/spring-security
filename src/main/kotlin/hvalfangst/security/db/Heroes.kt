package hvalfangst.security.db

import org.jetbrains.exposed.sql.Table

object Heroes : Table("heroes") {
    val id = integer("id").autoIncrement()
    val userId = integer("user_id").references(Users.id)
    val name = varchar("name", 45)
    val classType = varchar("class", 45)
    val level = integer("level")
    val hitPoints = integer("hit_points")
    val attack = integer("attack")
    val damage = integer("damage")
    val ac = integer("ac")
}