package com.example

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import java.util.UUID
import javax.transaction.Transactional

data class Request(val id: UUID)

@Controller("/")
open class Controller(
    private val coroutinesRepository: CoroutinesRepository,
) {

    @Transactional
    @Post(uri = "/suspend")
    open suspend fun createSuspend(@Body request: Request): String {
        coroutinesRepository.save(Data(id = request.id))
        // Exception: duplicate row
        coroutinesRepository.save(Data(id = request.id))
        return "ok"
    }
}
