package hvalfangst.security.model

import com.fasterxml.jackson.annotation.JsonProperty


data class Hero(
    @JsonProperty("name") val name: String,
    @JsonProperty("classType") val classType: String,
    @JsonProperty("level") val level: Int,
    @JsonProperty("hitPoints") val hitPoints: Int,
    @JsonProperty("attack") val attack: Int,
    @JsonProperty("damage") val damage: Int,
    @JsonProperty("ac") val ac: Int
)