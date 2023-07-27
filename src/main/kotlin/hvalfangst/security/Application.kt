package hvalfangst.security

import hvalfangst.security.dto.AssignRoleRequest
import hvalfangst.security.dto.CreateHeroRequest
import hvalfangst.security.dto.CreateUserRequest
import hvalfangst.security.service.HeroesService
import hvalfangst.security.service.UserService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application(private val userService: UserService, private val heroesService: HeroesService) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {

        val firstUser = userService.createUser(CreateUserRequest("John Read", "john.read@gmail.com", "Liverpool95"))

        if (firstUser != null) {
            userService.assignRoleToUser(firstUser, AssignRoleRequest.READER.roleId)
            heroesService.createHero(
                CreateHeroRequest(
                    firstUser,
                    "Oddvar Odelsgutt",
                    "Knight",
                    1,
                    25,
                    2,
                    4,
                    10
                )
            )
        }


        val secondUser = userService.createUser(CreateUserRequest("Michael Jones", "not.that.mj@blizzard.com", "qwerty123"))

        if (secondUser != null) {
            userService.assignRoleToUser(secondUser, AssignRoleRequest.CREATOR.roleId)
            heroesService.createHero(
                CreateHeroRequest(
                    secondUser,
                    "Kristian Klyse",
                    "Bard",
                    5,
                    5,
                    2,
                    5,
                    2
                )
            )
        }


        val thirdUser = userService.createUser(CreateUserRequest("Sarah Magnolia", "flowergirl92@hotmail.com", "RosesAreRed"))

        if (thirdUser != null) {
            userService.assignRoleToUser(thirdUser, AssignRoleRequest.EDITOR.roleId)
            heroesService.createHero(
                CreateHeroRequest(
                    thirdUser,
                    "Gustav Garvestoff",
                    "Alchemist",
                    12,
                    50,
                    20,
                    100,
                    10
                )
            )
        }


        val fourthUser = userService.createUser(CreateUserRequest("Eric Bakersfield", "eb.and.flow@sourcemasters.com", "livetsuger256"))

        if (fourthUser != null) {
            userService.assignRoleToUser(fourthUser, AssignRoleRequest.ADMIN.roleId)
            heroesService.createHero(
                CreateHeroRequest(
                    fourthUser,
                    "Olav den Hellige",
                    "Paladin",
                    17,
                    350,
                    20,
                    35,
                    50
                )
            )
        }

    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
