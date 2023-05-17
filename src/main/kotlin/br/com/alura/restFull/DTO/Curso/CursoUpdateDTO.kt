package br.com.alura.restFull.DTO.Curso

import jakarta.validation.constraints.Size

data class CursoUpdateDTO (
    val id: Long,
    @field:Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    var nome: String?,
    @field:Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    var categoria: String?
)