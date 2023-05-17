package br.com.alura.restFull.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}