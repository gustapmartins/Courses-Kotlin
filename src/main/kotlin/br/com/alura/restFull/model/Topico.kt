package br.com.alura.restFull.model

import jakarta.persistence.*

@Entity
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var titulo: String,
    var mensagem: String,
    @ManyToOne
    val curso: Curso,
    @ManyToOne
    val autor: Autor
)