package hvalfangst.security.db

import org.jetbrains.exposed.sql.Table

object UsersRoles : Table(name = "users_roles") {
    val userId = integer("user_id").references(Users.id)
    val roleId = integer("role_id").references(Roles.id)
    override val primaryKey = PrimaryKey(userId, roleId)
}
