package br.com.alura.restFull.controller

import br.com.alura.restFull.DTO.Curso.CursoDTO
import br.com.alura.restFull.DTO.Curso.CursoUpdateDTO
import br.com.alura.restFull.DTO.Curso.CursoView
import br.com.alura.restFull.model.Curso
import br.com.alura.restFull.service.CursoService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cursos")
class CursoController(private val service: CursoService) {

    @GetMapping
    @Cacheable("cursos")
    fun listCursos(
        @RequestParam() nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["id"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<CursoView> {
        return service.listCursos(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    fun listAutorId(@PathVariable id: Long): Curso {
        return service.getIdCursos(id)
    }

    @PostMapping
    @CacheEvict(value = ["cursos"], allEntries = true)
    fun createList(@RequestBody create: CursoDTO): CursoDTO {
        return service.createList(create)
    }

    @PatchMapping("/{id}")
    @CacheEvict(value = ["cursos"], allEntries = true)
    fun update(@PathVariable id: Long, @RequestBody  update: CursoUpdateDTO): CursoView {
        return service.updateList(id, update)
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = ["cursos"], allEntries = true)
    fun delete(@PathVariable id: Long): CursoView {
        return service.deleteId(id)
    }
}