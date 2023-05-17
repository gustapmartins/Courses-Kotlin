package br.com.alura.restFull.repository

import br.com.alura.restFull.model.Curso
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
    fun findByNome(nomeCurso: String, paginacao: Pageable): Page<Curso>
}