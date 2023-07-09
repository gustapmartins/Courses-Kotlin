package br.com.alura.restFull.controller

import br.com.alura.restFull.DTO.User.*
import br.com.alura.restFull.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val service: UserService,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/login")
    fun login(@RequestBody login: LoginDTO): String {
       return service.login(login, authenticationManager)
    }

    @PostMapping("/register")
    fun register(@RequestBody register: RegisterDTO): RegisterDTO {
        return service.register(register, passwordEncoder)
    }

    @PostMapping("/forgot-password")
    fun forgot(@RequestBody request: ForgotPasswordRequestDTO) {
        return service.forgotPassword(request.email)
    }

    @PostMapping("/reset-password")
    fun reset(@RequestBody reset: ResetPasswordDTO) {
        return service.resetPassword(reset, passwordEncoder)
    }
}