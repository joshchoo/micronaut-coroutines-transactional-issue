package com.example

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID

@MappedEntity("data")
data class Data(
    @field:Id val id: UUID
)

@JdbcRepository(dialect = Dialect.POSTGRES)
interface CoroutinesRepository : CoroutineCrudRepository<Data, UUID>
