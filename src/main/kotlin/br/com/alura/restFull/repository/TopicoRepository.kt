package br.com.alura.restFull.repository

import br.com.alura.restFull.DTO.Topico.TopicoPorCategoriaDTO
import br.com.alura.restFull.model.Topico
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository: JpaRepository<Topico, Long> {

    @Query(value = "SELECT new br.com.alura.restFull.DTO.Topico.TopicoPorCategoriaDTO(curso.categoria, count(t)) FROM Topico t JOIN t.curso curso GROUP BY curso.categoria")
    fun relatorio(): List<TopicoPorCategoriaDTO>
}