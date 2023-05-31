package br.com.alura.restFull.service

import br.com.alura.restFull.model.Usuario
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {
    fun notificar(email: String, subject: String, text: String) {
        val message = SimpleMailMessage()

        message.setTo(email);
        message.subject = subject
        message.text = text

        javaMailSender.send(message)
    }
}