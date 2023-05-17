package br.com.alura.restFull.mapper

import br.com.alura.restFull.DTO.Curso.CursoUpdateDTO
import br.com.alura.restFull.DTO.Curso.CursoView
import br.com.alura.restFull.model.Curso
import org.springframework.stereotype.Component

@Component
class CursoViewMapper: Mapper<Curso, CursoView> {
    override fun map(t: Curso): CursoView {
        return CursoView(
            id = t.id,
            nome = t.nome,
            categoria = t.categoria
        )
    }

    fun mapUpdate(update: CursoUpdateDTO, curso: Curso): CursoView {
        return CursoView(
            id = curso.id,
            nome = update.nome ?: curso.nome,
            categoria = update.categoria ?: curso.categoria
        )
    }
}