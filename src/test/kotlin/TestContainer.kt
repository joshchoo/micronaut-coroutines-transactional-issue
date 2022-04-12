package com.example

import org.testcontainers.containers.PostgreSQLContainerProvider

object TestContainer {
    private var postgres = PostgreSQLContainerProvider().newInstance("14.2")

    fun start() {
        postgres.start()
        System.setProperty("datasources.default.url", postgres.jdbcUrl)
        System.setProperty("datasources.default.username", postgres.username)
        System.setProperty("datasources.default.password", postgres.password)
    }

    fun stop() {
        postgres.stop()
    }
}
