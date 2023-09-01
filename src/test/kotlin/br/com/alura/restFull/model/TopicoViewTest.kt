package br.com.alura.restFull.model

import br.com.alura.restFull.DTO.Topico.TopicoView
import jakarta.persistence.Column
import java.time.LocalDate

object TopicoViewTest {
    fun build() = TopicoView(
        id = 1,
        titulo = "Aprendendo Kotlin basico",
        mensagem = "Aprendendo Kotlin",
        LocalDate.now(),
        autor = Autor(
            id = 0,
            nome = "Nome do Autor"
        ),
        curso = Curso(
            id = 1,
            nome = "nomeDo",
            categoria = "Nome da Categoria"
        )
    )
}