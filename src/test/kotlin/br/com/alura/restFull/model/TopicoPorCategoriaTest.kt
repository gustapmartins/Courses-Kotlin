package br.com.alura.restFull.model

import br.com.alura.restFull.DTO.Topico.TopicoPorCategoriaDTO

object TopicoPorCategoriaTest {
    fun build() = TopicoPorCategoriaDTO(
        categoria = "Kotlin",
        quantidade = 1
    )
}