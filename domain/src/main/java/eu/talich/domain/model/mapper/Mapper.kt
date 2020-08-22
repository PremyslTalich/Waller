package eu.talich.domain.model.mapper

interface Mapper<in I, out O> {
    fun map(from: I): O
}