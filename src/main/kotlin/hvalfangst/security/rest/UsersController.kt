package hvalfangst.security.rest

import hvalfangst.security.dto.*
import hvalfangst.security.jwt.JwtUtil
import hvalfangst.security.model.*
import hvalfangst.security.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class UsersController(
    private val userService: UserService,
    private val jwtUtil: JwtUtil,
    private val authManager: AuthenticationManager
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)


    @PostMapping("/api/users/create")
    fun createUser(@RequestBody userRequest: CreateUserRequest): ResponseEntity<String> {
        userService.createUser(userRequest)
        return ResponseEntity.ok("User created successfully.")
    }

    @PostMapping("/api/users/{userId}/roles")
    fun assignRoleToUser(@PathVariable userId: Int, @RequestParam request: AssignRoleRequest): ResponseEntity<String> {
        userService.assignRoleToUser(userId, request.roleId)
        logger.info("\n - - - - Role '$request' assigned to user with ID $userId.- - - - \n\n")
        return ResponseEntity.ok("Role '$request' assigned to user with ID $userId.")
    }

    @GetMapping("/api/users/{userId}/roles")
    fun getRolesForUser(@PathVariable userId: Int): ResponseEntity<List<Role>> {
        val roles: List<Role> = userService.getUserRoles(userId)
        return ResponseEntity.ok(roles)
    }

    @PostMapping("/api/users/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<*> {
        return try {
            val authentication: Authentication = authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    request.email,
                    request.password
                )
            )

            val userDetails: CustomUserDetails = authentication.principal as CustomUserDetails
            val accessToken: String = jwtUtil.generateToken(userDetails.username)
            val response = AuthResponse(userDetails.username, accessToken)

            ResponseEntity.ok().body(response)
        } catch (ex: BadCredentialsException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build<Any>()
        }
    }
}