package br.com.alura.restFull.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var titulo: String,
    var mensagem: String,
    @ManyToOne
    val curso: Curso,
    @ManyToOne
    val autor: Autor,
    var dataAlteracao: LocalDate? = null
)