package br.com.alura.restFull.model

import br.com.alura.restFull.DTO.Topico.TopicoDTO

object TopicoTest {
    fun build() = Topico(
        id = 1,
        titulo = "Aprendendo Kotlin basico",
        mensagem = "Aprendendo Kotlin",
        curso = CursoTest.build(),
        autor = AutorTest.build()
    )

    fun buildDto() = TopicoDTO(
        id = 1,
        titulo = "Aprendendo Kotlin basico",
        mensagem = "Aprendendo Kotlin",
        idAutor = 1,
        idCurso = 1
    )
}