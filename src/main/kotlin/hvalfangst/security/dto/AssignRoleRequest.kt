package hvalfangst.security.dto

enum class AssignRoleRequest(val roleId: Int) {
    HEROES_READ(1),
    HEROES_WRITE(2),
    HEROES_EDIT(3),
    HEROES_DELETE(4)
}