package br.com.alura.restFull.DTO.User

data class LoginDTO(
    var id: Long,
    val email: String,
    val senha: String
)