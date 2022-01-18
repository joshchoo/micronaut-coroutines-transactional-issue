package com.example

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import reactor.core.publisher.Mono
import java.util.UUID
import javax.transaction.Transactional

data class Request(val id: UUID)

@Controller("/")
open class Controller(
    private val coroutinesRepository: CoroutinesRepository,
    private val reactorRepository: ReactorRepository
) {

    @Transactional
    @Post(uri = "/suspend")
    open suspend fun createSuspend(@Body request: Request): String {
        coroutinesRepository.save(Data(id = request.id))
        // Exception: duplicate row
        coroutinesRepository.save(Data(id = request.id))
        return "ok"
    }

    @Transactional
    @Post(uri = "/reactor")
    open fun createReactor(@Body request: Mono<Request>): Mono<String> {
        return request.flatMap {
            reactorRepository.save(Data(id = it.id))
        }.flatMap {
            // Exception: duplicate row
            reactorRepository.save(Data(id = it.id))
        }.map {
            "ok"
        }
    }
}