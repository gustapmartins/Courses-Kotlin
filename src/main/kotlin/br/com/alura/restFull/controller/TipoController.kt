package br.com.alura.restFull.controller

import br.com.alura.restFull.DTO.Topico.TopicoDTO
import br.com.alura.restFull.DTO.Topico.TopicoPorCategoriaDTO
import br.com.alura.restFull.DTO.Topico.TopicoView
import br.com.alura.restFull.DTO.Topico.UpdateDTO
import br.com.alura.restFull.service.TopicoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/topicos")
class TipoController(private val service: TopicoService) {

    @GetMapping
    fun listar(@RequestParam("page") page: Int, @RequestParam("pageSize") pageSize: Int): List<TopicoView> {
        return this.service.listar().drop(page).take(pageSize)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun listID(@PathVariable id: Long): TopicoView {
        return this.service.listID(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody @Valid topicoDto: TopicoDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoDTO> {
        val uri = uriBuilder.path("/topicos/${topicoDto.id}").build().toUri()
        return ResponseEntity.created(uri).body(service.createList(topicoDto))
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateList(@PathVariable id: Long, @RequestBody updateDto: UpdateDTO): ResponseEntity<TopicoView> {
        return ResponseEntity.ok(service.updateList(id, updateDto))
    }

    @DeleteMapping("/{id}")
    fun deleteList(@PathVariable id: Long): ResponseEntity<TopicoView> {
        return ResponseEntity.ok(service.deleteList(id))
    }


    @GetMapping("/relatorio")
    fun relatorio(): List<TopicoPorCategoriaDTO> {
        return service.relatorio()
    }
}