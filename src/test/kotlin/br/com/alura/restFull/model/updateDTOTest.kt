package br.com.alura.restFull.model

import br.com.alura.restFull.DTO.Topico.UpdateDTO

object updateDTOTest {
    fun build() = UpdateDTO(
        id = 1,
        titulo = "Aprendendo Kotlin",
        mensagem = "Aprendendo Kotlin"
    )
}