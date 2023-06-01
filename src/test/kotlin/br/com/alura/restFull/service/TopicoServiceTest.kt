package br.com.alura.restFull.service

import br.com.alura.restFull.mapper.TopicoViewMapper
import br.com.alura.restFull.model.*
import br.com.alura.restFull.repository.TopicoRepository
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class TopicoServiceTest {
    private lateinit var repository: TopicoRepository
    private lateinit var cursoService: CursoService
    private lateinit var autorService: AutorService
    private lateinit var topicoViewMapper: TopicoViewMapper
    private lateinit var topicoService: TopicoService

    @BeforeEach
    fun setup() {
        repository = mockk()
        cursoService = mockk()
        autorService = mockk()
        topicoViewMapper = mockk()
        topicoService = TopicoService(repository, cursoService, autorService, topicoViewMapper)
    }

    @Test
    fun `Deve me retornar todos os topicos`() {
        every { repository.findAll() } returns listOf(TopicoTest.build())
        every { topicoViewMapper.map(TopicoTest.build()) } returns TopicoViewTest.build()

        val result = topicoService.listar()

        // Assert
        Assertions.assertEquals(1, result.size)
        Assertions.assertEquals(TopicoViewTest.build(), result[0])

        verify { repository.findAll() }
        verify { topicoViewMapper.map(TopicoTest.build()) }
    }

    @Test
    fun `Deve me retornar o topico especifico`() {
        // Arrange
        val topicoId = 1L
        every { repository.findById(topicoId) } returns Optional.of(TopicoTest.build())
        every { topicoViewMapper.map(TopicoTest.build()) } returns TopicoViewTest.build()
        // Act
        val result = topicoService.listID(topicoId)
        // Assert
        Assertions.assertEquals(TopicoViewTest.build(), result)

        verify { repository.findById(topicoId) }
        verify { topicoViewMapper.map(TopicoTest.build()) }
    }

    @Test
    fun `Deve criar um novo Topico`() {

        every { cursoService.getIdCursos(TopicoTest.buildDto().idCurso) } returns CursoTest.build()
        every { autorService.listAutorId(TopicoTest.buildDto().idAutor) } returns AutorTest.build()
        every { repository.save(any()) } returns TopicoTest.build()

        // Act
        val result = topicoService.createList(TopicoTest.buildDto())

        // Assert
        Assertions.assertEquals(TopicoTest.buildDto(), result)

        verify { cursoService.getIdCursos(TopicoTest.buildDto().idCurso) }
        verify { autorService.listAutorId(TopicoTest.buildDto().idAutor) }
        verify { repository.save(any()) }
    }

    @Test
    fun `Deve deletar um topico por id`() {

        every { repository.findById(TopicoTest.build().id) } returns Optional.of(TopicoTest.build())
        every { repository.deleteById(TopicoTest.build().id) } just Runs
        every { topicoViewMapper.map(TopicoTest.build()) } returns TopicoViewTest.build()

        val result = topicoService.deleteList(TopicoTest.build().id)

        Assertions.assertEquals(TopicoViewTest.build(), result)

        verify { repository.findById(TopicoTest.build().id) }
        verify { repository.deleteById(TopicoTest.build().id) }
        verify { topicoViewMapper.map(TopicoTest.build()) }
    }

    @Test
    fun `Deve atualizar o topico por id`() {

        val update = updateDTOTest.build()

        val updatedTopico = TopicoTest.build().copy(titulo = update.titulo ?: TopicoTest.build().titulo, mensagem = update.mensagem ?: TopicoTest.build().mensagem)

        every { repository.findById(TopicoTest.build().id) } returns Optional.of(TopicoTest.build())
        every { repository.save(updatedTopico) } returns updatedTopico
        every { topicoViewMapper.mapUpdate(update, updatedTopico) } returns TopicoViewTest.build()

        val result = topicoService.updateList(1, update)

        Assertions.assertEquals(TopicoViewTest.build(), result)

        verify { repository.findById(TopicoTest.build().id) }
        verify { repository.save(updatedTopico) }
        verify { topicoViewMapper.mapUpdate(update, updatedTopico) }
    }
}