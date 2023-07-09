package br.com.alura.restFull.config

import br.com.alura.restFull.repository.UserRepository
import br.com.alura.restFull.service.UserService
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class FilterToken(
   private val service: UserService,
    private val repository: UserRepository
): OncePerRequestFilter() {
    fun getSubject(token: String): String {
        return JWT.require(Algorithm.HMAC256("secreta"))
            .withIssuer("topicos")
            .build().verify(token).subject
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String;

        var authorizationHeader = request.getHeader("Authorization")

        if(authorizationHeader != null) {
            token = authorizationHeader.split(" ")[1]
            val subject = getSubject(token)
            val usuario = this.repository.findByEmail(subject)
            var authentication = UsernamePasswordAuthenticationToken(usuario, null, usuario?.authorities)

            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }
}