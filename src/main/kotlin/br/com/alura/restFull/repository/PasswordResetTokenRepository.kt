package br.com.alura.restFull.repository

import br.com.alura.restFull.DTO.User.PasswordResetToken
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PasswordResetTokenRepository: JpaRepository<PasswordResetToken, Long> {
    fun findByToken(token: String): Optional<PasswordResetToken>
    fun deleteByToken(token: String)
}