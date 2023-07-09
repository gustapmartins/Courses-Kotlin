package br.com.alura.restFull.DTO.User

import jakarta.validation.constraints.*


data class RegisterDTO(
    
    var id: Long,
    @field:NotEmpty(message = "Preencha esse campo") @Size(min = 8, max = 8, message = "O nome não pode ser menor que 8 caracteres")
    val nome: String,
    @field:NotEmpty(message = "Preencha esse campo") @Email(message = "Formato de e-mail invalido")
    val email: String,
    @field:NotEmpty(message = "Preencha esse campo") @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "A senha deve ter pelo menos 8 caracteres, contendo pelo menos uma letra e um número.")
    val senha: String
) {

}
