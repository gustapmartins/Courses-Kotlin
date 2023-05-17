package br.com.alura.restFull.DTO.Autor

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AutorDTO (
    val id: Long,
    @field:NotBlank(message = "O nome não pode estar em branco")
    @field:Size(max = 30, message = "O nome deve ter no máximo 30 caracteres")
    val nome: String
)