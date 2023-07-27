package hvalfangst.security.rest

import hvalfangst.security.dto.*
import hvalfangst.security.model.*
import hvalfangst.security.service.HeroesService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class HeroesController(
    private val heroesService: HeroesService
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    // - - - - - - |REQUIRES ROLE: READER| - - - - - -

    @Secured("READER")
    @GetMapping("/api/heroes/list/{userId}")
    fun getHeroesForUser(@PathVariable userId: Int): ResponseEntity<List<Hero>> {
        val heroes: List<Hero> = heroesService.getHeroesByUserId(userId)
        return ResponseEntity.ok(heroes)
    }

    @Secured("READER")
    @GetMapping("/api/heroes/get/{heroId}")
    fun getHeroById(@PathVariable heroId: Int): ResponseEntity<Hero> {
        val hero: Hero = heroesService.getHeroById(heroId)
        return ResponseEntity.ok(hero)
    }

    // - - - - - - |REQUIRES ROLE: CREATOR| - - - - - -

    @Secured("CREATOR")
    @PostMapping("/api/heroes/create")
    fun createHero(@RequestBody request: CreateHeroRequest): ResponseEntity<String> {
        val heroId: Int = heroesService.createHero(request)
        logger.info("- - - - Hero with ID $heroId has been created - - - - ")
        return ResponseEntity.ok("Hero with ID $heroId has been created")
    }

    // - - - - - - |REQUIRES ROLE: EDITOR| - - - - - -

    @Secured("EDITOR")
    @PutMapping("/api/heroes/edit/{heroId}")
    fun editHero(@PathVariable heroId: Int, @RequestBody request: UpdateHeroRequest): ResponseEntity<String> {
        heroesService.updateHero(heroId, request)
        logger.info(" - - - - Hero with ID $heroId has been updated - - - - ")
        return ResponseEntity.ok("Hero with ID $heroId has been updated")
    }

    // - - - - - - |REQUIRES ROLE: ADMIN| - - - - - -

    @Secured("ADMIN")
    @DeleteMapping("/api/heroes/delete/{heroId}")
    fun deleteHero(@PathVariable heroId: Int): ResponseEntity<String> {
        heroesService.deleteHero(heroId)
        logger.info("- - - - Hero with ID $heroId has been deleted - - - -")
        return ResponseEntity.ok("Hero with ID $heroId has been deleted")
    }



}