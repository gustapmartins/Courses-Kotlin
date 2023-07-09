package br.com.alura.restFull.DTO.Topico

import br.com.alura.restFull.model.Autor
import br.com.alura.restFull.model.Curso
import java.time.LocalDate

data class TopicoView (
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val dataAlteracao: LocalDate?,
    val autor: Autor,
    val curso: Curso
)