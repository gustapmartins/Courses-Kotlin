package br.com.alura.restFull

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class RestFullApplication

fun main(args: Array<String>) {
    runApplication<RestFullApplication>(*args)
}
