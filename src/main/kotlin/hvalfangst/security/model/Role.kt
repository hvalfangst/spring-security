package hvalfangst.security.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Role(
    @JsonProperty("id") val id: Int,
    @JsonProperty("name") val name: String
)