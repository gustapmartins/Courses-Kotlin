package br.com.alura.restFull.mapper

import br.com.alura.restFull.DTO.Autor.AutorDTO
import br.com.alura.restFull.model.Autor
import org.springframework.stereotype.Component

@Component
class AutorViewMapper: Mapper<Autor, AutorDTO> {
    override fun map(t: Autor): AutorDTO {
        return AutorDTO(
            id = t.id,
            nome = t.nome
        )
    }
}