package br.com.alura.restFull.DTO.Topico

import jakarta.validation.constraints.Size

data class UpdateDTO (
    var id: Long?,
    @Size(min = 5, max = 50)
    var titulo: String?,
    @Size(min = 5, max = 50)
    var mensagem: String?
)
