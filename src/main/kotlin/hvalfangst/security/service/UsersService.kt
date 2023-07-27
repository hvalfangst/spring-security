package hvalfangst.security.service

import hvalfangst.security.db.Roles
import hvalfangst.security.db.Users
import hvalfangst.security.db.UsersRoles
import hvalfangst.security.dto.CreateUserRequest
import hvalfangst.security.model.Role
import hvalfangst.security.model.User
import hvalfangst.security.jwt.BCryptHasher
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class UserService {

    fun createUser(request: CreateUserRequest): Int? {
        return transaction {
            val insertedUser = Users.insert {
                it[email] = request.email
                it[fullName] = request.fullName
                it[password] = BCryptHasher.encodePassword(request.password)
                it[enabled] = true
            }
            insertedUser[Users.id]
        }
    }

    fun assignRoleToUser(userId: Int, roleId: Int) {
        transaction {
            UsersRoles.insert  {
                it[UsersRoles.userId] = userId
                it[UsersRoles.roleId] = roleId
            }
        }
    }

    fun getUserRoles(userId: Int): List<Role> {
        return transaction {
            (Roles innerJoin UsersRoles innerJoin Users)
                .select { Users.id eq userId }
                .map { row ->
                    Role(
                        row[Roles.id],
                        row[Roles.name]
                    )
                }
        }
    }

    fun getUserByEmail(email: String): User? {
        return transaction {
            Users.select { Users.email eq email }
                .map { row ->
                    User(
                        row[Users.id],
                        row[Users.email],
                        row[Users.fullName],
                        row[Users.password],
                        row[Users.enabled]
                    )
                }
                .singleOrNull()
        }
    }

}
