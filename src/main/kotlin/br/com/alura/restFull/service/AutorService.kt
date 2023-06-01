package br.com.alura.restFull.service

import br.com.alura.restFull.DTO.Autor.AutorDTO
import br.com.alura.restFull.exception.NotFoundException
import br.com.alura.restFull.mapper.AutorViewMapper
import br.com.alura.restFull.model.Autor
import br.com.alura.restFull.repository.AutorRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class AutorService(
    private val repository: AutorRepository,
    private val AutorViewMapper: AutorViewMapper

) {
    val notFoundMessage: String = "Topico não encontrado!"

    fun listAutor(): List<AutorDTO> {
        val autorDtos = repository.findAll().stream()
            .map { t -> AutorViewMapper.map(t) }
            .collect(Collectors.toList())
            .sortedByDescending { t -> t.id }

        return autorDtos
    }

    fun listAutorId(id: Long): Autor {
        val AutorId = repository.findById(id).get()
        return AutorId
    }

    fun createList(create: AutorDTO): AutorDTO {
        val autors = Autor(
            id = create.id,
            nome = create.nome,
        )
        repository.save(autors)
        return create
    }

    fun updateId(id: Long, update: AutorDTO): Autor {
        val topico = repository.findById(id).orElseThrow{ NotFoundException(notFoundMessage) }
        var atualizado = topico.copy(
            nome = update.nome ?: topico.nome
        )
        repository.save(atualizado)
        return atualizado
    }

    fun deleteId(id: Long): Autor {
        val autorId = repository.findById(id).orElseThrow{ NotFoundException(notFoundMessage) }
        repository.deleteById(id)
        return autorId
    }
}