package br.com.alura.restFull.DTO.Curso

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


data class CursoDTO (
    val id: Long,
    @field:NotBlank(message = "O nome não pode estar em branco")
    @field:Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    val nome: String,
    @field:NotBlank(message = "O nome não pode estar em branco")
    @field:Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    val categoria: String
)