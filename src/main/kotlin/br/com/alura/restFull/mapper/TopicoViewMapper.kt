package br.com.alura.restFull.mapper

import br.com.alura.restFull.DTO.Topico.TopicoView
import br.com.alura.restFull.DTO.Topico.UpdateDTO
import br.com.alura.restFull.model.Topico
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class TopicoViewMapper: Mapper<Topico, TopicoView> {
    override fun map(t: Topico): TopicoView {
        return TopicoView(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            dataAlteracao = t.dataAlteracao
        )
    }

    fun mapUpdate(update: UpdateDTO, topico: Topico): TopicoView {
        return TopicoView(
            id = topico.id,
            titulo = update.titulo ?: topico.titulo,
            mensagem = update.mensagem ?: topico.mensagem,
            dataAlteracao = topico.dataAlteracao
        )
    }
}