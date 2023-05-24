package br.com.alura.restFull.service

import br.com.alura.restFull.model.Usuario
import br.com.alura.restFull.repository.UserRepository
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.security.auth.Subject

@Service
class UserService(
    private val repository: UserRepository,

): UserDetailsService {
    init {
        var staticUser = Usuario(
            nome = "Kleber",
            email = "Kleber@gmail.com",
            senha = "\$2a\$12\$xVgQwvvWurUQQjZ16aCdEOdZUXmU4olIIXSayO3pienqIiaCjpBka"
        )


        repository.save(staticUser)
    }
    fun gerarToken(user: Usuario): String {
        return JWT
            .create()
            .withIssuer("Topicos")
            .withSubject(user.username)
            .withClaim("id", user.id)
            .withExpiresAt(LocalDateTime.now().plusSeconds(30).toInstant(ZoneOffset.of("-03:00")))
            .sign(Algorithm.HMAC256("secreta"))
    }

    fun getSubject(token: String): String {
        return JWT.require(Algorithm.HMAC256("secreta"))
            .withIssuer("Topcios")
            .build().verify(token).subject
    }

    fun listUser(): List<Usuario> {
        return repository.findAll()
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        return repository.findByEmail(email) ?: throw RuntimeException()
    }
}