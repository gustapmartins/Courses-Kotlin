package br.com.alura.restFull.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "topico")
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long,
    @Column(name = "titulo")
    var titulo: String,
    @Column(name = "mensagem")
    var mensagem: String,
    @ManyToOne
    val curso: Curso,
    @ManyToOne
    val autor: Autor,
    @Column(name = "data_alteracao")
    var dataAlteracao: LocalDate? = null
)