package hvalfangst.security.rest

import com.fasterxml.jackson.databind.ObjectMapper
import hvalfangst.security.dto.*
import hvalfangst.security.jwt.BCryptHasher
import hvalfangst.security.jwt.JwtUtil
import hvalfangst.security.model.Role
import hvalfangst.security.model.User
import hvalfangst.security.service.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class UsersControllerTest {

    private lateinit var mockMvc: MockMvc

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var jwtUtil: JwtUtil

    @Mock
    private lateinit var authManager: AuthenticationManager

    @InjectMocks
    private lateinit var usersController: UsersController

    private val objectMapper = ObjectMapper()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build()
    }

    @Test
    fun testCreateUser() {
        val userRequest = CreateUserRequest(
            email = "test@example.com",
            fullName = "Test User",
            password = "test_password"
        )
        val userId = 1

        `when`(userService.createUser(userRequest)).thenReturn(userId)

        mockMvc.perform(
            post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("User with ID $userId has been created"))
    }

    @Test
    fun testAssignRoleToUser() {
        val userId = 1
        val role = AssignRoleRequest.ADMIN

        mockMvc.perform(
            post("/api/users/{userId}/roles", userId)
                .param("role", role.name)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("User with id $userId has been assigned the role ${role.name}"))
    }

    @Test
    fun testGetRolesForUser() {
        val userId = 1
        val roles = listOf(Role(1, "ROLE_ADMIN"), Role(2, "ROLE_EDITOR"))

        `when`(userService.getUserRoles(userId)).thenReturn(roles)

        mockMvc.perform(
            get("/api/users/{userId}/roles", userId)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(roles)))
    }

    @Test
    fun testLogin_Successful() {
        val email = "test@example.com"
        val password = "test_password"
        val user = User(
            id = 1,
            email = email,
            fullName = "Test User",
            password = BCryptHasher.encodePassword(password),
            enabled = true
        )
        val accessToken = "dummy_token"
        val authResponse = AuthResponse(user.email, accessToken)

        // Create a mock of Authentication and set principal to a CustomUserDetails instance
        val authentication = mock(Authentication::class.java)
        `when`(authentication.principal).thenReturn(CustomUserDetails(user, userService))

        `when`(authManager.authenticate(UsernamePasswordAuthenticationToken(email, password)))
            .thenReturn(authentication) // Return the mocked authentication
        `when`(userService.getUserByEmail(email)).thenReturn(user)
        `when`(jwtUtil.generateToken(email)).thenReturn(accessToken)

        mockMvc.perform(
            post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(LoginRequest(email, password)))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(authResponse)))
    }


    @Test
    fun testLogin_InvalidCredentials() {
        val email = "test@example.com"
        val password = "test_password"

        `when`(authManager.authenticate(UsernamePasswordAuthenticationToken(email, password)))
            .thenThrow(BadCredentialsException("Invalid credentials"))

        mockMvc.perform(
            post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(LoginRequest(email, password)))
        )
            .andExpect(MockMvcResultMatchers.status().isUnauthorized)
    }
}
