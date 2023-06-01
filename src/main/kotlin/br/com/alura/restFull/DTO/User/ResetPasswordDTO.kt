package br.com.alura.restFull.DTO.User

data class ResetPasswordDTO(
    val token: String,
    val newPassword: String
) {
}