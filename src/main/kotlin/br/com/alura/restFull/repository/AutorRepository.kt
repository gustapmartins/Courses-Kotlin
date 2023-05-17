package br.com.alura.restFull.repository

import br.com.alura.restFull.model.Autor
import org.springframework.data.jpa.repository.JpaRepository

interface AutorRepository: JpaRepository<Autor, Long> {
}