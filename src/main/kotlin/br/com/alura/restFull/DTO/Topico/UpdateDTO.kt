package br.com.alura.restFull.DTO.Topico

import br.com.alura.restFull.model.Autor
import br.com.alura.restFull.model.Curso
import jakarta.validation.constraints.Size

data class UpdateDTO (
    var id: Long?,
    @Size(min = 5, max = 50)
    var titulo: String?,
    @Size(min = 5, max = 50)
    var mensagem: String?,
    var autor: Autor?,
    var curso: Curso?
)
