package hvalfangst.security.service

import hvalfangst.security.dto.CustomUserDetails
import hvalfangst.security.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException


class UserDetailsServiceImpl(private val userService: UserService) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails? {
        val user: User? = userService.getUserByEmail(email)
        if(user != null) {
            return CustomUserDetails(user, userService)
        }
        throw UsernameNotFoundException("Could not find user")
    }
}