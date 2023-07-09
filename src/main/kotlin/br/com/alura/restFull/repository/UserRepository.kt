package br.com.alura.restFull.repository

import br.com.alura.restFull.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<Usuario, Long> {
    fun findByEmail(email: String?): Usuario?
}