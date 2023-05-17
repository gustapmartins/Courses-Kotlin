package br.com.alura.restFull.DTO.Topico

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class TopicoDTO(
    var id: Long,
    @field:NotEmpty @Size(min = 5, max = 50)
    val titulo: String,
    @field:NotEmpty @Size(min = 5, max = 50)
    val mensagem: String,
    @field:NotNull
    val idCurso: Long,
    @field:NotNull
    val idAutor: Long
)