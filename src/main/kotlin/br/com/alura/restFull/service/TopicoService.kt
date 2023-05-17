package br.com.alura.restFull.service

import br.com.alura.restFull.DTO.Topico.TopicoDTO
import br.com.alura.restFull.DTO.Topico.TopicoPorCategoriaDTO
import br.com.alura.restFull.DTO.Topico.TopicoView
import br.com.alura.restFull.DTO.Topico.UpdateDTO
import br.com.alura.restFull.exception.NotFoundException
import br.com.alura.restFull.mapper.TopicoViewMapper
import br.com.alura.restFull.model.Topico
import br.com.alura.restFull.repository.TopicoRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val cursoService: CursoService,
    private val autorService: AutorService,
    private val TopicoViewMapper: TopicoViewMapper,
) {
    private val notFoundMessage: String = "Topico não encontrado!"

    fun listar(): List<TopicoView> {
        return repository.findAll().stream()
            .map { t -> TopicoViewMapper.map(t) }
            .collect(Collectors.toList())
    }

    fun listID(id: Long): TopicoView {
        val topico = repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        return TopicoViewMapper.map(topico)
    }

    fun createList(create: TopicoDTO): TopicoDTO {
        val topico = Topico(
            id = create.id,
            titulo = create.titulo,
            mensagem = create.mensagem,
            curso = cursoService.getIdCursos(create.idCurso),
            autor = autorService.listAutorId(create.idAutor)
        )
        repository.save(topico)
        return create
    }

    fun updateList(id: Long, update: UpdateDTO): TopicoView {
        val topico = repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        var atualizado = topico.copy(
            titulo = update.titulo ?: topico.titulo,
            mensagem = update.mensagem ?: topico.mensagem
        )
        repository.save(atualizado)
        return TopicoViewMapper.mapUpdate(update, atualizado)
    }

    fun deleteList(id: Long): TopicoView {
        val topico = repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        repository.deleteById(id)
        return TopicoViewMapper.map(topico)
    }

    fun relatorio(): List<TopicoPorCategoriaDTO> {
        return repository.relatorio()
    }
}