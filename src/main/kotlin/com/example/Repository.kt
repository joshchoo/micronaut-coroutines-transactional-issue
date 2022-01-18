package com.example

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import io.micronaut.data.repository.reactive.ReactorCrudRepository
import java.util.UUID

@MappedEntity("data")
data class Data(
    @field:Id val id: UUID
)

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface CoroutinesRepository : CoroutineCrudRepository<Data, UUID>

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface ReactorRepository : ReactorCrudRepository<Data, UUID>
