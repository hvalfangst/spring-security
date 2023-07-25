package hvalfangst.security.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UserRole(
    @JsonProperty("userId") val userId: Int,
    @JsonProperty("roleId") val roleId: Int
)