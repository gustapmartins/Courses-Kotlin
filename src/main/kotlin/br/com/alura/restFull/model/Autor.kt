package br.com.alura.restFull.model

import jakarta.persistence.*

@Entity
@Table(name = "autor")
data class Autor (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long,
    @Column(name = "nome")
    val nome: String,
)