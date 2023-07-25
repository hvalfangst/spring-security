package hvalfangst.security.dto

import hvalfangst.security.model.User
import hvalfangst.security.service.UserService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(private val user: User, private val userService: UserService) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        val roles = userService.getUserRoles(user.id)
        return roles.map { role -> SimpleGrantedAuthority(role.name) }
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return user.enabled == true
    }
}

