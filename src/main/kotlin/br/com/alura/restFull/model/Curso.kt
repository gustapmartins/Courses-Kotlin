package br.com.alura.restFull.model

import jakarta.persistence.*

@Entity
@Table(name = "curso")
data class Curso (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "nome")
    val nome: String,
    @Column(name = "categoria")
    val categoria: String
)
