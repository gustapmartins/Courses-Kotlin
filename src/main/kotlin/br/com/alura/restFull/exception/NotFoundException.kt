package br.com.alura.restFull.exception

import java.lang.RuntimeException

class NotFoundException(message: String?): RuntimeException(message) {

}