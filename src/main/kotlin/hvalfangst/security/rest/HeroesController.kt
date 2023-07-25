package hvalfangst.security.rest

import hvalfangst.security.dto.*
import hvalfangst.security.model.*
import hvalfangst.security.service.HeroesService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class HeroesController(
    private val heroesService: HeroesService
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/api/heroes/create")
    fun createHero(@RequestBody request: CreateHeroRequest): ResponseEntity<String> {
        heroesService.createHero(request)
        return ResponseEntity.ok("Hero created")
    }

    @GetMapping("/api/heroes/list/{userId}")
    fun getHeroesForUser(@PathVariable userId: Int): ResponseEntity<List<Hero>> {
        val heroes: List<Hero> = heroesService.getHeroesByUserId(userId)
        return ResponseEntity.ok(heroes)
    }
}