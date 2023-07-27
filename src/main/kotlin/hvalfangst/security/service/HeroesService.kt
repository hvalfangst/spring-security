package hvalfangst.security.service

import hvalfangst.security.db.Heroes
import hvalfangst.security.dto.CreateHeroRequest
import hvalfangst.security.dto.UpdateHeroRequest
import hvalfangst.security.model.Hero
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Service

@Service
class HeroesService() {

    fun createHero(request: CreateHeroRequest): Int {
        return transaction {
           val insertedHero = Heroes.insert {
                it[userId] = request.userId
                it[name] = request.name
                it[classType] = request.classType
                it[level] = request.level
                it[hitPoints] = request.hitPoints
                it[attack] = request.attack
                it[damage] = request.damage
                it[ac] = request.ac
            }
            insertedHero[Heroes.id]
        }
    }

    fun getHeroById(heroId: Int): Hero {
        return transaction {
            Heroes.select { Heroes.id eq heroId }.map {
                Hero(
                    classType = it[Heroes.classType],
                    level = it[Heroes.level],
                    hitPoints = it[Heroes.hitPoints],
                    attack = it[Heroes.attack],
                    damage = it[Heroes.damage],
                    ac = it[Heroes.ac],
                    name = it[Heroes.name]
                )
            }.firstOrNull() ?: throw Exception("Hero not found")
        }
    }

    fun getHeroesByUserId(userId: Int): List<Hero> {
        return transaction {
            Heroes.select { Heroes.userId eq userId }.map {
                Hero(
                    classType = it[Heroes.classType],
                    level = it[Heroes.level],
                    hitPoints = it[Heroes.hitPoints],
                    attack = it[Heroes.attack],
                    damage = it[Heroes.damage],
                    ac = it[Heroes.ac],
                    name = it[Heroes.name]
                )
            }
        }
    }

    fun updateHero(heroId: Int, request: UpdateHeroRequest) {
        transaction {
            Heroes.update({ Heroes.id eq heroId }) {
                it[name] = request.name
                it[classType] = request.classType
                it[level] = request.level
                it[hitPoints] = request.hitPoints
                it[attack] = request.attack
                it[damage] = request.damage
                it[ac] = request.ac
            }
        }
    }

    fun deleteHero(heroId: Int) {
        transaction {
            Heroes.deleteWhere { Heroes.id eq heroId }
        }
    }




}