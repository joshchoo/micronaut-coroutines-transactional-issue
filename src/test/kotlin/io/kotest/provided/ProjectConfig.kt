package io.kotest.provided

import com.example.TestContainer
import io.kotest.core.config.AbstractProjectConfig
import io.micronaut.test.extensions.kotest.MicronautKotestExtension

object ProjectConfig : AbstractProjectConfig() {
    override fun listeners() = listOf(MicronautKotestExtension)
    override fun extensions() = listOf(MicronautKotestExtension)

    override fun beforeAll() {
        TestContainer.start()
    }

    override fun afterAll() {
        TestContainer.stop()
    }
}
