package br.com.alura.restFull.service

import br.com.alura.restFull.DTO.Curso.CursoDTO
import br.com.alura.restFull.DTO.Curso.CursoUpdateDTO
import br.com.alura.restFull.DTO.Curso.CursoViewDto
import br.com.alura.restFull.exception.NotFoundException
import br.com.alura.restFull.mapper.CursoViewMapper
import br.com.alura.restFull.model.Curso
import br.com.alura.restFull.repository.CursoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CursoService(
    private val repository: CursoRepository,
    private val CursoViewMapper: CursoViewMapper
) {
    private val notFoundMessage: String = "Topico não encontrado!"

    fun listCursos(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<CursoViewDto> {
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByNome(nomeCurso, paginacao)
        }
        return topicos.map { t ->
            CursoViewMapper.map(t)
        }
    }

    fun getIdCursos(id: Long): Curso {
        return repository.findById(id).get()
    }

    fun createList(create: CursoDTO): CursoDTO {
        val cursos = Curso(
            id = create.id,
            nome = create.nome,
            categoria = create.categoria,
        )
        repository.save(cursos)
        return create
    }

    fun updateList(id: Long, update: CursoUpdateDTO): CursoViewDto {
        val curso = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        var atualizado = curso.copy(
            nome = update.nome ?: curso.nome,
            categoria = update.categoria ?: curso.categoria
        )

        repository.save(atualizado)
        return CursoViewMapper.mapUpdate(update, atualizado)
    }

    fun deleteId(id: Long): CursoViewDto {
        val curso = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        repository.deleteById(id)
        return CursoViewMapper.map(curso)
    }
}