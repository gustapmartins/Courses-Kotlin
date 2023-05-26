package br.com.alura.restFull.controller

import br.com.alura.restFull.model.Usuario
import br.com.alura.restFull.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val service: UserService,
    private val authenticationManager: AuthenticationManager,
) {

    @GetMapping
    fun listUser(): List<Usuario> {
        return this.service.listUser()
    }

    @PostMapping()
    fun login(@RequestBody login: Usuario): String {
        val token = UsernamePasswordAuthenticationToken(login.email, login.password)
        val authenticate = authenticationManager.authenticate(token)
        var user = authenticate.principal as Usuario

        return service.gerarToken(user)
    }
}