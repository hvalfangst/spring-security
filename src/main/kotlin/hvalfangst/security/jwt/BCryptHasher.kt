package hvalfangst.security.jwt

import org.mindrot.jbcrypt.BCrypt

class BCryptHasher {
    companion object {

        fun encodePassword(password: String): String {
            return BCrypt.hashpw(password, BCrypt.gensalt(14))
        }

        fun checkPassword(password: String, hashedPassword: String): Boolean {
            return BCrypt.checkpw(password, hashedPassword)
        }

    }
}