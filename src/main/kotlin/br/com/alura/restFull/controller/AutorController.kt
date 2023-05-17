package br.com.alura.restFull.controller

import br.com.alura.restFull.DTO.Autor.AutorDTO
import br.com.alura.restFull.model.Autor
import br.com.alura.restFull.service.AutorService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/autor")
class AutorController(
    private val service: AutorService
) {

    @GetMapping
    fun listAutor(): List<AutorDTO> {
        return service.listAutor()
    }

    @GetMapping("/{id}")
    fun listAutorId(@PathVariable id: Long): Autor {
        return service.listAutorId(id)
    }

    @PostMapping
    fun createList(@RequestBody @Valid create: AutorDTO): AutorDTO {
        return service.createList(create)
    }

    @PatchMapping("/{id}")
    fun updateId(@PathVariable id: Long, update: AutorDTO): Autor {
        return service.updateId(id, update)
    }

    @DeleteMapping("/{id}")
    fun deleteId(@PathVariable id: Long): Autor {
        return service.deleteId(id)
    }
}