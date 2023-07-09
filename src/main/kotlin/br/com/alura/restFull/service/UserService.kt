package br.com.alura.restFull.service

import br.com.alura.restFull.DTO.User.LoginDTO
import br.com.alura.restFull.model.PasswordResetToken
import br.com.alura.restFull.DTO.User.RegisterDTO
import br.com.alura.restFull.DTO.User.ResetPasswordDTO
import br.com.alura.restFull.model.Usuario
import br.com.alura.restFull.repository.PasswordResetTokenRepository
import br.com.alura.restFull.repository.UserRepository
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class UserService(
    private val repository: UserRepository,
    private val emailService: EmailService,
    private val passwordResetTokenRepository: PasswordResetTokenRepository
): UserDetailsService {
    fun gerarToken(user: Usuario): String {
        return JWT
            .create()
            .withIssuer("topicos")
            .withSubject(user.username)
            .withClaim("id", user.id)
            .withExpiresAt(LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00")))
            .sign(Algorithm.HMAC256("secreta"))
    }

    fun login(login: LoginDTO, authenticationManager: AuthenticationManager): String {
        val token = UsernamePasswordAuthenticationToken(login.email, login.senha)
        val authenticate = authenticationManager.authenticate(token)
        var user = authenticate.principal as Usuario

        return gerarToken(user)
    }

    fun register(register: RegisterDTO, passwordEncoder: PasswordEncoder): RegisterDTO {
        // Verifique se o email já está cadastrado
        if (repository.findByEmail(register.email) != null) {
            throw RuntimeException("Email já cadastrado")
        }

        // Crie um novo objeto Usuario com os dados do registro
        val novoUsuario = Usuario(
            nome = register.nome,
            email = register.email,
            senha = passwordEncoder.encode(register.senha)
        )

        // Salve o novo usuário no repositório
        repository.save(novoUsuario)
        return register
    }

    fun forgotPassword(email: String) {
        val token = Math.floor(Math.random() * 9999).toString()
        val passwordResetToken = PasswordResetToken(token = token, email = email)

        passwordResetTokenRepository.save(passwordResetToken)
        emailService.notificar(email = email, subject = "Recuperação de senha", text = "token da senha: $token")
    }

    fun resetPassword(reset: ResetPasswordDTO, passwordEncoder: PasswordEncoder) {
        val passwordResetToken = passwordResetTokenRepository.findByToken(reset.token)
            .orElseThrow { RuntimeException("Invalid token") }

        val user = repository.findByEmail(passwordResetToken.email)
            ?: throw RuntimeException("User with email ${passwordResetToken.email} not found.")

        user.senha = passwordEncoder.encode(reset.newPassword)
        repository.save(user)
        passwordResetTokenRepository.deleteByToken(reset.token)
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        return repository.findByEmail(email) ?: throw RuntimeException()
    }
}