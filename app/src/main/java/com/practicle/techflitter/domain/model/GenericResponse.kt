package com.practicle.techflitter.domain.model

data class GenericResponse<T>(
    var meta: Meta,
    val data: T?,
)
