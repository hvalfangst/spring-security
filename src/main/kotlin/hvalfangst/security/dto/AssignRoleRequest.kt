package hvalfangst.security.dto

enum class AssignRoleRequest(val roleId: Int) {
    READER(1),
    CREATOR(2),
    EDITOR(3),
    ADMIN(4)
}