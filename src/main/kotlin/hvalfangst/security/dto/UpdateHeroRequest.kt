package hvalfangst.security.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateHeroRequest(
    @JsonProperty("userId") val userId: Int,
    @JsonProperty("name") val name: String,
    @JsonProperty("class") val classType: String,
    @JsonProperty("level") val level: Int,
    @JsonProperty("hitPoints") val hitPoints: Int,
    @JsonProperty("attack") val attack: Int,
    @JsonProperty("damage") val damage: Int,
    @JsonProperty("ac") val ac: Int)