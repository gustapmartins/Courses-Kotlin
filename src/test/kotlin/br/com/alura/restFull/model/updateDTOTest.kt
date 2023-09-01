package br.com.alura.restFull.model

import br.com.alura.restFull.DTO.Topico.UpdateDTO

object updateDTOTest {
   fun build() = UpdateDTO(
       id = 1,
       titulo = "Aprendendo Kotlin",
       mensagem = "Aprendendo Kotlin",
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