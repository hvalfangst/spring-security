package hvalfangst.security.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateUserRequest(
    @JsonProperty("fullname") val fullName: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("password")val password: String)