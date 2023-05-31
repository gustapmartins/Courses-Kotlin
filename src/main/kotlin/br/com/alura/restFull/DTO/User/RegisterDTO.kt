package br.com.alura.restFull.DTO.User

import jakarta.validation.constraints.*

class RegisterDTO(
    var id: Long,
    @field:NotEmpty(message = "Preencha esse campo") @Size(min = 8, max = 8, message = "O nome não pode ser menor que 8 caracteres")
    val nome: String,
    @field:NotEmpty(message = "Preencha esse campo") @Email(message = "Formato de e-mail invalido")
    val email: String,
    @field:NotEmpty(message = "Preencha esse campo") @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "A senha deve ter pelo menos 8 caracteres, contendo pelo menos uma letra e um número.")
    val senha: String
) {

}


//^(?=.*[A-Za-z]): Pelo menos uma letra (maiúscula ou minúscula) deve estar presente.
//(?=.*\d): Pelo menos um dígito (0-9) deve estar presente.
//[A-Za-z\d]{8,}: Permite uma combinação de letras (maiúsculas ou minúsculas) e dígitos.