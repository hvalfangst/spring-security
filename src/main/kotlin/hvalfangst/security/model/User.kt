package hvalfangst.security.model

import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    @JsonProperty("id") val id: Int,
    @JsonProperty("email") val email: String,
    @JsonProperty("fullName") val fullName: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("enabled") val enabled: Boolean?
)