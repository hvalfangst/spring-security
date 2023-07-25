package hvalfangst.security.dto

enum class AssignRoleRequest(val roleId: Int) {
    USER(1),
    CREATOR(2),
    EDITOR(3),
    ADMIN(4)
}