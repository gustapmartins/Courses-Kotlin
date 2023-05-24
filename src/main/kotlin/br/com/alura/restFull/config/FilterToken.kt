package br.com.alura.restFull.config

import br.com.alura.restFull.repository.UserRepository
import br.com.alura.restFull.service.UserService
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
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String;

        var authorizationHeader = request.getHeader("Authorization")

        if(authorizationHeader != null) {
            token = authorizationHeader.replace("Bearer", "")
            val subject = this.service.getSubject(token)
            val usuario = this.repository.findByEmail(subject)
            var authentication = UsernamePasswordAuthenticationToken(usuario, null, usuario?.authorities)

            SecurityContextHolder.getContext().setAuthentication(authentication)
        }

        filterChain.doFilter(request, response)
    }
}