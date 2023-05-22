package br.com.alura.restFull.service

import br.com.alura.restFull.model.Usuario
import br.com.alura.restFull.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository,

): UserDetailsService {
    init {
        var staticUser = Usuario(
            nome = "Kleber",
            email = "Kleber@gmail.com",
            senha = "\$2a\$12\$5C8vLlX/wVZHnxJT65Qru.uqXujXCVAvn3aLX43rnv7Syhae5PXrm"
        )

        repository.save(staticUser)
    }
    fun listUser(): List<Usuario> {
        return repository.findAll()
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        return repository.findByEmail(email) ?: throw RuntimeException()
    }
}