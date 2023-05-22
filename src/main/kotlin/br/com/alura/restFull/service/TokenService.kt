package br.com.alura.restFull.service

import br.com.alura.restFull.model.Usuario
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService {
    fun gerarToken(user: Usuario): String {
        return JWT
            .create()
            .withIssuer("Topicos")
            .withSubject(user.username)
            .withClaim("id", user.id)
            .withExpiresAt(LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00")))
            .sign(Algorithm.HMAC256("secreta"))
    }
}
