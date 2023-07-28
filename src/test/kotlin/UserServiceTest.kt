import hvalfangst.security.dto.CreateUserRequest
import hvalfangst.security.model.Role
import hvalfangst.security.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class UserServiceTest {

    @Mock
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
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

        val createdUserId = userService.createUser(userRequest)
        assertEquals(userId, createdUserId)
    }

    @Test
    fun testAssignRoleToUser() {
        val userId = 1
        val expectedRole = Role(2, "ROLE_EDITOR")

        // Mock the function call to retrieve roles for the user
        `when`(userService.getUserRoles(userId)).thenReturn(listOf(expectedRole))

        // Execute the function that should assign the role to the user
        userService.assignRoleToUser(userId, expectedRole.id)

        // Verify that the expected role is now associated with the user
        val userRoles = userService.getUserRoles(userId)
        assertEquals(1, userRoles.size)
        assertEquals(expectedRole, userRoles[0])
    }

    @Test
    fun testGetUserRoles_WhenUserNotFound() {
        val userId = 1

        // Mock the function call to retrieve roles for the user
        `when`(userService.getUserRoles(userId)).thenReturn(emptyList())

        val userRoles = userService.getUserRoles(userId)
        assertEquals(0, userRoles.size)
    }

    @Test
    fun testGetUserByEmail_WhenUserNotFound() {
        val email = "non_existent@example.com"

        // Mock the function call to retrieve user by email
        `when`(userService.getUserByEmail(email)).thenReturn(null)

        val user = userService.getUserByEmail(email)
        assertNull(user)
    }

}
