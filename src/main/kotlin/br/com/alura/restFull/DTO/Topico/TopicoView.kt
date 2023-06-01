package br.com.alura.restFull.DTO.Topico

import java.time.LocalDate

data class TopicoView (
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val dataAlteracao: LocalDate?
)