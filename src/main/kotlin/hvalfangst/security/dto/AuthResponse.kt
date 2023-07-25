package hvalfangst.security.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthResponse(
    @JsonProperty("email") val email: String,
    @JsonProperty("token")val token: String)