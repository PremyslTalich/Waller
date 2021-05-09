package eu.talich.waller.library.mapper.model

interface Mapper<in I, out O> {
    fun map(from: I): O
}