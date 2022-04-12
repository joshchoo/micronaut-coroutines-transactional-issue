package com.example

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import java.util.UUID

@Client("/")
interface HttpClient {

    @Post("/suspend")
    fun postRequest(id: UUID)
}

@MicronautTest(transactional = false)
class RollbackFailureTest(
    private val client: HttpClient,
    private val repository: CoroutinesRepository,
) : FunSpec({
    afterEach {
        repository.deleteAll()
    }

    test("no data should be saved when an error occurs") {
        shouldThrow<HttpClientResponseException> {
            client.postRequest(UUID.randomUUID())
        }

        repository.count() shouldBe 0
    }
})
